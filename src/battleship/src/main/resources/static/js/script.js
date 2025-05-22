const baseUrl = "http://localhost:3000";
const GRID_SIZE = 10;

const possibleColors = [
  "#ed5f55",
  "#f7ac57",
  "#cfed55",
  "#55eda6",
  "#64f0f5",
  "#8164f5",
  "#c564f5",
  "#ff8cd7",
  "#a86f1e",
];

const playerGrid = document.getElementById("player-grid");
const computerGrid = document.getElementById("computer-grid");
const startGameBtn = document.getElementById("start-game-btn");
const resetGameBtn = document.getElementById("reset-game-btn");
const logTableBodyEl = document.getElementById("log-table-body");
const logContainerEl = document.getElementById("log-container");

/**
 * @typedef {Object} Point
 * @property x
 * @property y
 */

/**
 * @typedef {Object} Ship
 * @property {Point[]} points The points occupied by the ship.
 * @property {String} color The color of the ship.
 */

/**
 * @typedef {"HIT" | "MISS" | "SUNK"} AttackResult
 */

/**
 * @typedef {Object} Hit
 * @extends Point
 * @property {string} result - The result of the attack (e.g., "HIT", "MISS", "SUNK").
 */

/**
 * @typedef {Object} GameUpdate
 * @property {Ship[]} playerShips - The player's ships.
 * @property {Hit[]} playerHits - The player's hits.
 * @property {Hit[]} computerHits - The computer's hits.
 */

/**
 * Creates an empty grid of cells within the given container.
 * @param {HTMLElement} container - The container element to append cells to.
 * @param {boolean} isComputerGrid - Whether this is the computer's grid (for event handling)
 */
function createEmptyGrid(container, isComputerGrid) {
  for (let i = 0; i < Math.pow(GRID_SIZE, 2); i++) {
    const cell = document.createElement("div");
    cell.classList.add("cell");

    if (isComputerGrid) {
      cell.style.pointerEvents = "none";
      cell.addEventListener("click", async () => attack(cell, i));
    }

    container.appendChild(cell);
  }
}

/**
 * Handles the attack on a cell in the computer's grid.
 *
 * @param {HTMLElement} cell
 * @param {number} cellIndex
 * @returns {Promise<void>}
 */
async function attack(cell, cellIndex) {
  try {
    const response = await fetch(`${baseUrl}/attack/${cellIndex}`, {
      method: "PUT",
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const result = await response.json();

    processUpdate(result["gameUpdate"]);

    const playerHit = result["playerHit"];
    const computerHit = result["computerHit"];
    const currentTurn = result["currentTurn"];

    addElementToLog(
      currentTurn,
      "Player",
      playerHit["x"],
      playerHit["y"],
      playerHit["result"],
    );
    addElementToLog(
      currentTurn,
      "Computer",
      computerHit["x"],
      computerHit["y"],
      computerHit["result"],
    );
  } catch (error) {
    console.error("Error attacking:", error);
    alert("Error attacking!");
  }
}

createEmptyGrid(playerGrid, false);
createEmptyGrid(computerGrid, true);

/**
 * Adds the 'ship' class to cells in a grid based on an array of indices.
 * @param {HTMLElement} gridElement - The grid element.
 * @param {Ship[]} ships - An array of ship objects, each containing an array of indices.
 */
function displayShips(gridElement, ships) {
  const cells = gridElement.querySelectorAll(".cell");

  for (let i = 0; i < ships.length; i++) {
    const ship = ships[i];
    const shipColor = possibleColors[i % possibleColors.length];

    for (const point of ship.points) {
      const index = point.x + GRID_SIZE * point.y;

      if (cells[index]) {
        cells[index].classList.add("ship");
        cells[index].style.backgroundColor = shipColor;
      }
    }
  }
}

// Start game button, which will fetch the game update and start the game
startGameBtn.addEventListener("click", async () => {
  startGameBtn.disabled = true;

  try {
    const response = await fetch(`${baseUrl}/new-game`, {
      method: "POST",
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    /** @type {GameUpdate} */
    const data = await response.json();
    startGame(data);
  } catch (error) {
    console.error("Error loading grids:", error);
    alert("Error loading grids!");
    startGameBtn.disabled = false;
  }
});

/**
 * Starts the game by processing the initial game data and toggling the visibility of the various buttons.
 *
 * @param {GameUpdate} data  - The game update object containing player ships and hits.
 */
function startGame(data) {
  processUpdate(data);

  // Enable the computer grid for interaction
  for (const cell of computerGrid.querySelectorAll(".cell")) {
    cell.style.pointerEvents = "auto";
  }

  startGameBtn.classList.add("d-none");
  resetGameBtn.classList.remove("d-none");
  logContainerEl.classList.remove("d-none");
}

/**
 * Processes the game update and updates the UI accordingly.
 *
 * @param {GameUpdate} gameUpdate - The game update object containing player ships and hits.
 */
function processUpdate(gameUpdate) {
  displayShips(playerGrid, gameUpdate.playerShips);
  displayHits(computerGrid, gameUpdate.computerHits);
  displayHits(playerGrid, gameUpdate.playerHits);
}

/**
 * Processes the hits and updates the Grids accordingly.
 * @param {HTMLElement} gridElement - The grid element.
 * @param hits - An array of hit objects containing x, y coordinates and result.
 */
function displayHits(gridElement, hits) {
  const cells = gridElement.querySelectorAll(".cell");

  for (const hit of hits) {
    const index = hit.x + GRID_SIZE * hit.y;

    if (cells[index]) {
      cells[index].classList.add(hit.result.toLowerCase());
    }
  }
}

/**
 * Adds a new row to the log table with the given parameters.
 *
 * @param {number} currentTurn
 * @param {string} attacker
 * @param {number} x
 * @param {number} y
 * @param {string} result
 */
function addElementToLog(currentTurn, attacker, x, y, result) {
  const row = document.createElement("tr");

  row.innerHTML = `
        <td>${currentTurn}</td>
        <td>${attacker}</td>
        <td>${x}</td>
        <td>${y}</td>
        <td>${result}</td>
    `;

  logTableBodyEl.prepend(row);
}

// Checks if the game has started and if so, fetches the game update, trying also to reconstruct the log
document.addEventListener("DOMContentLoaded", async () => {
  const hasStarted = await fetch(`${baseUrl}/has-started`).then((response) => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json();
  });

  if (hasStarted["hasStarted"]) {
    const data = await fetch(`${baseUrl}/get-update`).then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    });

    inferLogFromHits(data["playerHits"], data["computerHits"]);

    startGame(data);
  }
});

/**
 * Attempts to infer the log from the hits of both players.
 *
 * @param {Hit[]} playerHits
 * @param {Hit[]} computerHits
 */
function inferLogFromHits(playerHits, computerHits) {
  for (let i = 0; i < playerHits.length + computerHits.length; i++) {
    const currentTurn = Math.floor(i / 2) + 1;
    const attacker = i % 2 === 0 ? "Player" : "Computer";
    const hit =
      i % 2 === 0 ? computerHits[i / 2] : playerHits[Math.floor(i / 2)];

    addElementToLog(currentTurn, attacker, hit["x"], hit["y"], hit["result"]);
  }
}

// Reset game button, which will reset the game and reload the page
resetGameBtn.addEventListener("click", async () => {
  resetGameBtn.disabled = true;
  try {
    const res = await fetch(`${baseUrl}/reset-game`, {
      method: "DELETE",
    });

    if (!res.ok) {
      throw new Error("Network response was not ok");
    }

    await res.json();

    location.reload();
  } catch (error) {
    console.error("Error resetting game:", error);
    alert("Error resetting game!");
    resetGameBtn.disabled = false;
  }
});
