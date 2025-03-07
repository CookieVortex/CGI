document.addEventListener("DOMContentLoaded", function () {
    const sides = document.querySelectorAll(".side");
    if (sides.length < 2) {
        console.error("Not enough elements with class .side");
        return;
    }

    const leftSide = sides[0];
    const rightSide = sides[sides.length - 1];

    const allSeats = [];

    function createSeatsForSide(side, startChar) {
        for (let row = 1; row <= 25; row++) {
            if (row === 3 || row === 10 || row === 11) continue; // Скрытые ряды

            let rowDiv = document.createElement("div");
            rowDiv.classList.add("row");

            let seatClass = (row === 1 || row === 2) ? "business-class" :
                (row >= 3 && row <= 6) ? "first-class" :
                    "economy-class";
            rowDiv.classList.add(seatClass);

            // Добавляем отступ для Business Class (1A - 2G) и First Class (3A - 6G)
            if (row === 1 || row === 2) {
                rowDiv.style.marginBottom = "20px";  // Отступ для бизнес-класса
            }
            if (row >= 3 && row <= 6) {
                rowDiv.style.marginBottom = "0px";  // Отступ для первого класса
            }

            // Проверяем, если это ряд 13, добавляем класс для "Large legroom"
            if (row === 13) {
                rowDiv.classList.add("large-legroom"); // Добавление для большого пространства для ног
            }

            // Создаем сиденья для этого ряда
            for (let seat = 1; seat <= 3; seat++) {
                let seatDiv = document.createElement("div");
                seatDiv.classList.add("seat", "available", seatClass); // Свободное место
                seatDiv.dataset.row = row;
                seatDiv.textContent = `${row}${String.fromCharCode(startChar + seat)}`;

                // Проверка перед кликом
                seatDiv.addEventListener("click", function () {
                    if (!seatDiv.classList.contains("occupied")) {
                        seatDiv.classList.toggle("selected");
                    }
                });

                allSeats.push(seatDiv);
                rowDiv.appendChild(seatDiv);
            }
            side.appendChild(rowDiv);
        }
    }




    createSeatsForSide(leftSide, 64);
    createSeatsForSide(rightSide, 68);

    // Функция для случайного занятия мест
    function randomOccupiedSeats(seatsArray, count = 20) {
        const randomIndices = new Set();
        while (randomIndices.size < count && randomIndices.size < seatsArray.length) {
            randomIndices.add(Math.floor(Math.random() * seatsArray.length));
        }
        randomIndices.forEach(index => {
            let seat = seatsArray[index];
            seat.classList.add("occupied");
            seat.classList.remove("available");
            seat.style.backgroundColor = "gray"; // Сделать визуально занятым
        });
    }

    // Генерируем случайные занятые места
    randomOccupiedSeats(allSeats, 20);

    // Фильтрация мест по чекбоксам
    document.querySelectorAll(".filter").forEach(filter => {
        filter.addEventListener("change", function () {
            let selectedClasses = [];
            let selectedRows = new Set();

            document.querySelectorAll(".filter:checked").forEach(cb => {
                if (cb.dataset.class) selectedClasses.push(cb.dataset.class);
                if (cb.dataset.row) selectedRows.add(parseInt(cb.dataset.row));
                if (cb.dataset.range) {
                    let [start, end] = cb.dataset.range.split("-").map(Number);
                    for (let i = start; i <= end; i++) selectedRows.add(i);
                }
            });

            allSeats.forEach(seat => {
                let seatRow = parseInt(seat.dataset.row);
                let seatClass = seat.classList.contains("business-class") ? "business-class" :
                    seat.classList.contains("first-class") ? "first-class" :
                        "economy-class";

                let matchesClass = selectedClasses.length === 0 || selectedClasses.includes(seatClass);
                let matchesRow = selectedRows.size === 0 || selectedRows.has(seatRow);

                if (matchesClass && matchesRow) {
                    seat.style.visibility = "visible";
                    seat.classList.remove("hidden-seat");
                } else {
                    seat.style.visibility = "hidden";
                    seat.classList.add("hidden-seat");
                }
            });
        });
    });
});
