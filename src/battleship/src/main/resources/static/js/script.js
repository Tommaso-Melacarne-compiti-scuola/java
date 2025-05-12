const baseUrl = "http://localhost:3000";

/**
 * Creates an empty grid of cells within the given container.
 * @param {HTMLElement} container - The container element to append cells to.
 * @param {boolean} isComputerGrid - Whether this is the computer's grid (for event handling)
 */
function createEmptyGrid(container, isComputerGrid) {
  for (let i = 0; i < 100; i++) {
    const cell = document.createElement("div");
    cell.classList.add("cell");

    if (isComputerGrid) {
      cell.addEventListener("click", getComputerEventListener(cell, i));
    }

    container.appendChild(cell);
  }
}

function getComputerEventListener(cell, cellIndex) {
  return async () => {
    try {
      const response = await fetch(`${baseUrl}/attack/${cellIndex}`, {
        method: "PUT",
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const result = await response.json();

      if (result["playerAttackResult"] === "HIT") {
        alert("Hit!");
        cell.style.backgroundColor = "red";
      } else if (result["playerAttackResult"] === "MISS") {
        alert("Miss!");
        cell.style.backgroundColor = "lightgrey";
      } else if (result["playerAttackResult"] === "SUNK") {
        alert("Sunk!");
        cell.style.backgroundColor = "darkred";
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
 * @param {number[]} shipIndices - An array of indices where ships are located.
 */
function displayShips(gridElement, shipIndices) {
  const cells = gridElement.querySelectorAll(".cell");

  shipIndices.forEach((index) => {
    if (cells[index]) {
      cells[index].classList.add("ship");
    }
  });
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
      // data = { player: [1, 23, 45], computer: [10, 20, 30] }
      displayShips(playerGrid, data.player);
      displayShips(computerGrid, data.computer);

      startGameBtn.classList.add("d-none");
    })
    .catch((error) => {
      console.error("Error loading grids:", error);
      alert("Error loading grids!");
      startGameBtn.disabled = false;
    });
});
