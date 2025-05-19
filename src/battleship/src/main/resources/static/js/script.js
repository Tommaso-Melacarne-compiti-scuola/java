const baseUrl = "http://localhost:3000";
const GRID_SIZE = 10;

const possibleColors = ['#ed5f55','#f7ac57','#cfed55','#55eda6','#64f0f5','#8164f5','#c564f5','#ff8cd7','#a86f1e']

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
      cell.addEventListener("click", getAttackEventListener(cell, i));
    }

    container.appendChild(cell);
  }
}

function getAttackEventListener(cell, cellIndex) {
  return async () => {
    try {
      const response = await fetch(`${baseUrl}/attack/${cellIndex}`, {
        method: "PUT",
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const result = await response.json();

      processUpdate(result.gameUpdate);

      if (result["playerAttackResult"] === "HIT") {
        alert("Hit!");
      } else if (result["playerAttackResult"] === "MISS") {
        alert("Miss!");
      } else if (result["playerAttackResult"] === "SUNK") {
        alert("Sunk!");
      }
    } catch (error) {
      console.error("Error attacking:", error);
      alert("Error attacking!");
    }
  };
}

const playerGrid = document.getElementById("player-grid");
const computerGrid = document.getElementById("computer-grid");

createEmptyGrid(playerGrid, false);
createEmptyGrid(computerGrid, true);

/**
 * Adds the 'ship' class to cells in a grid based on an array of indices.
 * @param {HTMLElement} gridElement - The grid element.
 * @param {Ship[]} ships - An array of ship objects, each containing an array of indices.
 */
function displayShips(gridElement, ships) {
  const cells = gridElement.querySelectorAll(".cell");

  for (let i = 0; i < ships.length; i++){
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

const startGameBtn = document.getElementById("start-game-btn");

startGameBtn.addEventListener("click", async () => {
  startGameBtn.disabled = true;

  await fetch(`${baseUrl}/new-game`, {
    method: "POST",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      processUpdate(data);

      startGameBtn.classList.add("d-none");
    })
    .catch((error) => {
      console.error("Error loading grids:", error);
      alert("Error loading grids!");
      startGameBtn.disabled = false;
    });
});

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
      if (hit.result === "HIT") {
        cells[index].style.backgroundColor = "red";
      } else if (hit.result === "MISS") {
        cells[index].style.backgroundColor = "lightgrey";
      } else if (hit.result === "SUNK") {
        cells[index].style.backgroundColor = "darkred";
      }
    }
  }
}
