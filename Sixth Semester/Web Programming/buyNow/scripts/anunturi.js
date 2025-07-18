document.addEventListener('DOMContentLoaded', function () {
    // const listContainer = document.getElementById("categorieList");

    // fetch("api/categorii/read.php")
    //     .then(response => response.json())
    //     .then(categorii => {
    //         listContainer.innerHTML = "";

    //         categorii.forEach(cat => {
    //             const li = document.createElement("li");
    //             li.innerHTML = `<a href="anunturi.html?categorie=${cat.id}">${cat.nume}</a>`;
    //             listContainer.appendChild(li);
    //         });
    //     })
    //     .catch(error => {
    //         console.error("Eroare la încărcarea categoriilor:", error);
    //     });
        
    // fetchAnunturi();

    // function fetchAnunturi() {
    //     fetch("api/anunturi/read.php")
    //         .then(response => response.json())
    //         .then(anunturi => {
    //             const tbody = document.querySelector(".anunturi-table tbody");
    //             tbody.innerHTML = "";

    //             anunturi.forEach(anunt => {
    //                 const tr = document.createElement("tr");

    //                 const tdImg = document.createElement("td");
    //                 tdImg.innerHTML = `
    //                     <img src="${anunt.image_url}" alt="${anunt.titlu}" style="width: 100px; height: auto;">
    //                     <br>
    //                     <a href="product-details.html?id=${anunt.id}">
    //                         <button class="anunturi-table-button-area-min">Licitează</button>
    //                     </a>
    //                 `;

    //                 const tdDescriere = document.createElement("td");
    //                 tdDescriere.textContent = anunt.titlu;

    //                 const tdDetalii = document.createElement("td");
    //                 tdDetalii.textContent = anunt.descriere;

    //                 const tdPret = document.createElement("td");
    //                 tdPret.setAttribute("data-sort", anunt.pret_actual);
    //                 tdPret.textContent = `${anunt.pret_actual} RON`;

    //                 const tdActiune = document.createElement("td");
    //                 tdActiune.className = "anunturi-table-td-area";
    //                 tdActiune.innerHTML = `
    //                     <a href="product-details.html?id=${anunt.id}">
    //                         <button>Licitează</button>
    //                     </a>
    //                 `;

    //                 tr.appendChild(tdImg);
    //                 tr.appendChild(tdDescriere);
    //                 tr.appendChild(tdDetalii);
    //                 tr.appendChild(tdPret);
    //                 tr.appendChild(tdActiune);

    //                 tbody.appendChild(tr);
    //             });
    //         })
    //         .catch(error => {
    //             console.error("Eroare la încărcarea anunțurilor:", error);
    //         });
    // }

    document.querySelectorAll('.sortable').forEach(table => {
        const headers = table.querySelectorAll('thead th');
        const tbody = table.querySelector('tbody');
        
        headers.forEach((header, index) => {
            if (header.id === 'th-actiune') return;
            if (header.id === 'th-imagine') return;
            
            header.addEventListener('click', () => {
                const isAscending = !header.classList.contains('sorted-asc');
                const direction = isAscending ? 1 : -1;
                
                headers.forEach(h => {
                    h.classList.remove('sorted-asc', 'sorted-desc', 'sorted');
                });
                
                header.classList.add('sorted');
                header.classList.add(isAscending ? 'sorted-asc' : 'sorted-desc');
                
                const rows = Array.from(tbody.querySelectorAll('tr'));
                
                rows.sort((rowA, rowB) => {
                    const cellA = rowA.cells[index];
                    const cellB = rowB.cells[index];
                    
                    const valueA = cellA.hasAttribute('data-sort') 
                        ? parseFloat(cellA.getAttribute('data-sort'))
                        : cellA.textContent.trim();
                    
                    const valueB = cellB.hasAttribute('data-sort') 
                        ? parseFloat(cellB.getAttribute('data-sort'))
                        : cellB.textContent.trim();
                    
                    if (!isNaN(valueA)) {
                        return (valueA - valueB) * direction;
                    } else {
                        return valueA.localeCompare(valueB) * direction;
                    }
                });
                
                rows.forEach(row => tbody.appendChild(row));
            });
        });
    });
});