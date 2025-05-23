const baseUrl = "http://localhost:3000";
const GRID_SIZE = 10;

// Ship configurations for placement
const SHIP_CONFIGS = [
  { name: "Carrier", length: 5 },
  { name: "Battleship", length: 4 },
  { name: "Cruiser", length: 3 },
  { name: "Submarine", length: 3 },
  { name: "Destroyer", length: 2 },
];

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
const placementControls = document.getElementById("placement-controls");
const shipsToPlace = document.getElementById("ships-to-place");
const resetPlacementBtn = document.getElementById("reset-placement-btn");

// Ship placement state
let selectedShipType = null;
let isHorizontal = true;
let placedShips = [];

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
    cell.dataset.index = i;

    const x = i % GRID_SIZE;
    const y = Math.floor(i / GRID_SIZE);
    cell.dataset.x = x;
    cell.dataset.y = y;

    if (isComputerGrid) {
      cell.style.pointerEvents = "none";
      cell.addEventListener("click", async () => attack(cell, i));
    } else {
      cell.addEventListener("mouseover", () => previewShipPlacement(cell));
      cell.addEventListener("mouseout", () => clearShipPlacementPreview());
      cell.addEventListener("click", () => placeShip(cell));
    }

    container.appendChild(cell);
  }
}

/**
 * Initializes the ship placement controls and event listeners.
 */
function initShipPlacement() {
  shipsToPlace.innerHTML = "";
  placedShips = [];

  startGameBtn.disabled = false;

  SHIP_CONFIGS.forEach((ship, index) => {
    const shipButton = document.createElement("div");
    shipButton.classList.add("ship-selection");
    shipButton.textContent = `${ship.name}`;
    shipButton.style.backgroundColor =
      possibleColors[index % possibleColors.length];
    shipButton.dataset.shipType = index;

    shipButton.addEventListener("click", () => {
      document.querySelectorAll(".ship-selection.selected").forEach((el) => {
        el.classList.remove("selected");
      });

      shipButton.classList.add("selected");
      selectedShipType = index;
    });

    shipsToPlace.appendChild(shipButton);
  });

  document.addEventListener("keydown", (e) => {
    if (
      e.key.toLowerCase() === "r" &&
      placementControls.classList.contains("d-none") === false
    ) {
      isHorizontal = !isHorizontal;

      // If hovering over a cell, update the preview
      const hoveredCell = playerGrid.querySelector(
        ".cell.placement-hover, .cell.placement-invalid",
      );
      if (hoveredCell) {
        clearShipPlacementPreview();
        previewShipPlacement(hoveredCell);
      }
    }
  });

  // Set up reset placement button
  resetPlacementBtn.addEventListener("click", resetShipPlacement);
}

/**
 * Preview ship placement when hovering over a cell
 * 
 * @param cell {HTMLElement} The cell element being hovered over.
 */
function previewShipPlacement(cell) {
  if (selectedShipType === null) return;

  if (placedShips.some((ship) => ship.shipType === selectedShipType)) return;

  const shipLength = SHIP_CONFIGS[selectedShipType].length;
  const startX = parseInt(cell.dataset.x);
  const startY = parseInt(cell.dataset.y);

  const shipCells = [];
  let isValid = true;

  for (let i = 0; i < shipLength; i++) {
    const x = isHorizontal ? startX + i : startX;
    const y = isHorizontal ? startY : startY + i;

    if (x >= GRID_SIZE || y >= GRID_SIZE) {
      isValid = false;
      break;
    }

    const index = x + y * GRID_SIZE;
    const shipCell = playerGrid.querySelector(`.cell[data-index="${index}"]`);

    if (!shipCell) {
      isValid = false;
      break;
    }

    if (shipCell.classList.contains("ship")) {
      isValid = false;
    }

    shipCells.push(shipCell);
  }

  shipCells.forEach((shipCell) => {
    shipCell.classList.add(isValid ? "placement-hover" : "placement-invalid");
  });
}

function clearShipPlacementPreview() {
  playerGrid
    .querySelectorAll(".cell.placement-hover, .cell.placement-invalid")
    .forEach((cell) => {
      cell.classList.remove("placement-hover", "placement-invalid");
    });
}

function placeShip(cell) {
  if (selectedShipType === null) return;

  if (placedShips.some((ship) => ship.shipType === selectedShipType)) return;

  const shipLength = SHIP_CONFIGS[selectedShipType].length;
  const startX = parseInt(cell.dataset.x);
  const startY = parseInt(cell.dataset.y);

  const shipCells = [];
  let isValid = true;

  for (let i = 0; i < shipLength; i++) {
    const x = isHorizontal ? startX + i : startX;
    const y = isHorizontal ? startY : startY + i;

    // Check if ship is within grid boundaries
    if (x >= GRID_SIZE || y >= GRID_SIZE) {
      isValid = false;
      break;
    }

    const index = x + y * GRID_SIZE;
    const shipCell = playerGrid.querySelector(`.cell[data-index="${index}"]`);

    if (!shipCell) {
      isValid = false;
      break;
    }

    if (shipCell.classList.contains("ship")) {
      isValid = false;
      break;
    }

    shipCells.push({ cell: shipCell, x, y });
  }

  if (!isValid) return;

  const shipColor = possibleColors[selectedShipType % possibleColors.length];
  const points = [];

  shipCells.forEach(({ cell, x, y }) => {
    cell.classList.add("ship");
    cell.style.backgroundColor = shipColor;
    points.push({ x, y });
  });

  placedShips.push({
    shipType: selectedShipType,
    position: { x: startX, y: startY },
    orientation: isHorizontal ? "HORIZONTAL" : "VERTICAL",
    length: shipLength,
    points,
  });

  const shipButton = document.querySelector(
    `.ship-selection[data-shipType="${selectedShipType}"]`,
  );
  if (shipButton) {
    shipButton.classList.remove("selected");
    shipButton.classList.add("placed");
  }

  selectedShipType = null;

  if (placedShips.length === SHIP_CONFIGS.length) {
    startGameBtn.disabled = false;
    setTimeout(() => {
      startGameBtn.disabled = false;
    }, 100);
  }
}

/**
 * Resets the ship placement by clearing all ships from the grid and resetting the state.
 */
function resetShipPlacement() {
  playerGrid.querySelectorAll(".cell").forEach((cell) => {
    cell.classList.remove("ship");
    cell.style.backgroundColor = "";
  });

  document.querySelectorAll(".ship-selection").forEach((shipBtn) => {
    shipBtn.classList.remove("selected", "placed");
  });

  placedShips = [];
  selectedShipType = null;

  startGameBtn.disabled = false;
}

// Convert placed ships to the format expected by the server
function getShipsForServer() {
  return placedShips.map((ship) => ({
    position: { x: ship.position.x, y: ship.position.y },
    length: ship.length,
    orientation: ship.orientation,
  }));
}

createEmptyGrid(playerGrid, false);
createEmptyGrid(computerGrid, true);
initShipPlacement();

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
  // Only validate if ships are partially placed
  if (placedShips.length > 0 && placedShips.length < SHIP_CONFIGS.length) {
    alert("Please either place all ships or none (for random placement)!");
    return;
  }

  // Clear any ship selection before starting the game
  selectedShipType = null;
  document.querySelectorAll(".ship-selection.selected").forEach((el) => {
    el.classList.remove("selected");
  });

  startGameBtn.disabled = true;

  try {
    let body = undefined;

    // Ships are manually placed
    if (placedShips.length !== 0) {
      let requestBody;

      // If ships have been manually placed, send them to the server
      if (placedShips.length === SHIP_CONFIGS.length) {
        requestBody = {
          ships: getShipsForServer(),
        };
      }

      body = JSON.stringify(requestBody);
    }

    const response = await fetch(`${baseUrl}/new-game`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body,
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
  placementControls.classList.add("d-none");

  for (const cell of computerGrid.querySelectorAll(".cell")) {
    cell.style.pointerEvents = "auto";
  }

  for (const cell of playerGrid.querySelectorAll(".cell")) {
    cell.removeEventListener("mouseover", previewShipPlacement);
    cell.removeEventListener("mouseout", clearShipPlacementPreview);
    cell.removeEventListener("click", placeShip);
  }

  playerGrid.style.cursor = "default";

  processUpdate(data);

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
  try {
    displayShips(playerGrid, gameUpdate.playerShips);
    displayHits(computerGrid, gameUpdate.computerHits);
    displayHits(playerGrid, gameUpdate.playerHits);

    // Check for game over after each update
    if (isGameOver(gameUpdate)) {
      handleGameOver(gameUpdate);
    }
  } catch (error) {
    console.error("Error in processUpdate:", error);
  }
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

      cells[index].style.pointerEvents = "none";
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

/**
 * Handles the attack on a cell in the computer's grid.
 *
 * @param {HTMLElement} cell
 * @param {number} cellIndex
 * @returns {Promise<void>}
 */
async function attack(cell, cellIndex) {
  // Don't allow attacks if the cell is already hit or game is over
  if (
    cell.classList.contains("hit") ||
    cell.classList.contains("miss") ||
    cell.classList.contains("sunk") ||
    cell.style.pointerEvents === "none"
  ) {
    return;
  }

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

/**
 * Checks if the game is over by checking if all ships of either player are sunk.
 * @param {GameUpdate} gameUpdate - The current game state.
 * @returns {boolean} True if the game is over.
 */
function isGameOver(gameUpdate) {
  try {
    const playerShipsSunk = countSunkShips(
      gameUpdate.playerShips,
      gameUpdate.playerHits,
    );

    const computerSunkCount = gameUpdate.computerHits.filter(
      (hit) => hit.result === "SUNK",
    ).length;

    return (
      playerShipsSunk >= SHIP_CONFIGS.length ||
      computerSunkCount >= SHIP_CONFIGS.length
    );
  } catch (error) {
    console.error("Error in isGameOver:", error);
    return false;
  }
}

/**
 * Counts how many ships are sunk for a player
 * @param {Ship[]} ships - The ships to check
 * @param {Hit[]} hits - The hits to check against
 * @returns {number} - The number of sunk ships
 */
function countSunkShips(ships, hits) {
  try {
    let sunkCount = 0;

    // Check each ship
    for (const ship of ships) {
      if (isShipSunk(ship, hits)) {
        sunkCount++;
      }
    }

    return sunkCount;
  } catch (error) {
    console.error("Error in countSunkShips:", error);
    return 0;
  }
}

/**
 * Checks if a specific ship is sunk
 * @param {Ship} ship - The ship to check
 * @param {Hit[]} hits - The hits to check against
 * @returns {boolean} - Whether the ship is sunk
 */
function isShipSunk(ship, hits) {
  try {
    for (const point of ship.points) {
      const isPointHit = hits.some(
        (hit) =>
          hit.x === point.x &&
          hit.y === point.y &&
          (hit.result === "HIT" || hit.result === "SUNK"),
      );

      if (!isPointHit) {
        return false;
      }
    }

    return true;
  } catch (error) {
    console.error("Error in isShipSunk:", error);
    return false;
  }
}

/**
 * Handles the end of the game.
 * @param {GameUpdate} gameUpdate - The current game state.
 */
function handleGameOver(gameUpdate) {
  try {
    // Disable all cells to prevent further attacks
    computerGrid.querySelectorAll(".cell").forEach((cell) => {
      cell.style.pointerEvents = "none";
    });

    const winner = determineWinner(gameUpdate);

    showWinnerModal(winner);
  } catch (error) {
    console.error("Error in handleGameOver:", error);
    alert("Game over!");
  }
}

/**
 * Determines the winner of the game.
 * @param {GameUpdate} gameUpdate - The current game state.
 * @returns {string} The name of the winner.
 */
function determineWinner(gameUpdate) {
  try {
    const computerSunkCount = gameUpdate.computerHits.filter(
      (hit) => hit.result === "SUNK",
    ).length;
    
    if (computerSunkCount >= SHIP_CONFIGS.length) {
      return "Player";
    } else {
      return "Computer";
    }
  } catch (error) {
    console.error("Error in determineWinner:", error);
    return "Unknown";
  }
}

const modalElement = document.getElementById("winnerModal");
const playAgainBtn = document.getElementById("play-again-btn");

/**
 * Shows the winner modal.
 * @param {string} winner - The name of the winner.
 */
function showWinnerModal(winner) {
  try {
    if (!modalElement) {
      console.error("Modal element not found!");
      alert(`Game Over! ${winner} wins!`);
      return;
    }

    const winnerName = document.getElementById("winner-name");
    const winnerMessage = document.getElementById("winner-message");
    const winnerIcon = document.getElementById("winner-icon");

    if (winnerName) {
      winnerName.textContent = winner;

      if (winner === "Player") {
        winnerName.classList.add("text-success");
      } else {
        winnerName.classList.add("text-danger");
      }
    }

    if (winnerMessage) {
      winnerMessage.textContent = `wins the battle!`;
    }

    if (winnerIcon) {
      if (winner === "Player") {
        winnerIcon.innerHTML = '<i class="bi bi-trophy text-success"></i>';
      } else {
        winnerIcon.innerHTML = '<i class="bi bi-robot text-danger"></i>';
      }
    }

    if (playAgainBtn) {
      const newPlayAgainBtn = playAgainBtn.cloneNode(true);
      playAgainBtn.parentNode.replaceChild(newPlayAgainBtn, playAgainBtn);

      newPlayAgainBtn.addEventListener("click", () => {
        const bootstrapModal = bootstrap.Modal.getInstance(modalElement);
        if (bootstrapModal) {
          bootstrapModal.hide();
        }

        resetGameBtn.click();
      });
    }

    try {
      const winnerModal = new bootstrap.Modal(modalElement);
      winnerModal.show();
    } catch (modalError) {
      console.error("Error showing modal:", modalError);
      alert(`Game Over! ${winner} wins!`);
    }
  } catch (error) {
    console.error("Error in showWinnerModal:", error);
    alert(`Game Over! ${winner} wins!`);
  }
}
