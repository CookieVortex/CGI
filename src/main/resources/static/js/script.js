// Helped chat gpt, I don't know javascript well //

document.addEventListener("DOMContentLoaded", function () {
    const sides = document.querySelectorAll(".side");
    if (sides.length < 2) {
        console.error("Not enough elements with class .side");
        return;
    }

    const leftSide = sides[0];
    const rightSide = sides[sides.length - 1];

    const occupiedSeats = [];
    const occupiedSeatsRight = [];

    // Function to create seats with different classes
    function createSeatsForSide(side, startChar, occupiedSeatsArray, classPrefix) {
        for (let row = 1; row <= 25; row++) {
            let isHiddenRow = row === 3 || row === 10 || row === 11;
            let rowDiv = document.createElement("div");
            rowDiv.classList.add("row");

            // Add different classes for seats
            let classForRow = (row === 1 || row === 2) ? "first-class" :
                (row >= 3 && row <= 6) ? "business-class" :
                    "economy-class";
            rowDiv.classList.add(classForRow);

            if (row == 1 || row == 2) rowDiv.classList.add("row-gap-first");
            if (row >= 4) rowDiv.classList.add("row-gap-later");

            // Creating seats in the row
            for (let seat = 1; seat <= 3; seat++) {
                let seatDiv = document.createElement("div");
                seatDiv.classList.add("seat", "available");

                if (!isHiddenRow) {
                    seatDiv.textContent = `${row}${String.fromCharCode(startChar + seat)}`;
                }

                if (seatDiv.textContent) {
                    occupiedSeatsArray.push(seatDiv);
                }

                seatDiv.onclick = function () {
                    this.style.backgroundColor = "green";
                };

                rowDiv.appendChild(seatDiv);
            }

            side.appendChild(rowDiv);
        }
    }

    // Generate seats for different sides (left and right)
    createSeatsForSide(leftSide, 64, occupiedSeats);
    createSeatsForSide(rightSide, 68, occupiedSeatsRight);

    // Function for randomly marking seats as occupied
    function randomOccupiedSeats(seatsArray, count = 20) {
        const randomIndices = new Set();
        while (randomIndices.size < count && randomIndices.size < seatsArray.length) {
            randomIndices.add(Math.floor(Math.random() * seatsArray.length));
        }
        randomIndices.forEach(index => seatsArray[index].classList.add("occupied"));
    }

    randomOccupiedSeats(occupiedSeats);
    randomOccupiedSeats(occupiedSeatsRight);
});
