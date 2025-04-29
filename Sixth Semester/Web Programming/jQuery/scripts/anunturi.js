document.addEventListener('DOMContentLoaded', function () {
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