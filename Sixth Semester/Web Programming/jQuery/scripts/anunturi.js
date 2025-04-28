function makeTablesSortable() {
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
}

function setupLiveSearch() {
    const searchInput = document.getElementById('searchInput');
    const suggestionsContainer = document.getElementById('searchSuggestions');
    const table = document.querySelector('.anunturi-table');
    const rows = table.querySelectorAll('tbody tr');
    
    const allItems = Array.from(rows).map(row => {
        const cells = row.querySelectorAll('td');
        return {
            element: row,
            text: Array.from(cells).slice(1, -1).map(cell => cell.textContent).join(' ').toLowerCase()
        };
    });
    
    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        filterTable(searchTerm);
        
        showSuggestions(searchTerm);
    });
    
    // Hide suggestions when clicking outside
    document.addEventListener('click', function(e) {
        if (!searchInput.contains(e.target) && !suggestionsContainer.contains(e.target)) {
            suggestionsContainer.style.display = 'none';
        }
    });
    
    function filterTable(searchTerm) {
        rows.forEach(row => {
            const match = allItems.find(item => item.element === row);
            if (match.text.includes(searchTerm) || searchTerm === '') {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }
    
    function showSuggestions(searchTerm) {
        if (searchTerm.length < 2) {
            suggestionsContainer.style.display = 'none';
            return;
        }
        
        // Find all matching items
        const matches = allItems.filter(item => 
            item.text.includes(searchTerm) && item.text !== searchTerm
        );
        
        // Create suggestion items
        suggestionsContainer.innerHTML = '';
        
        if (matches.length > 0) {
            matches.slice(0, 5).forEach(match => {
                const suggestionText = match.text.length > 50 
                    ? match.text.substring(0, 50) + '...' 
                    : match.text;
                
                const suggestionItem = document.createElement('div');
                suggestionItem.className = 'search-suggestion-item';
                
                // Highlight matching parts
                const startIdx = match.text.indexOf(searchTerm);
                const endIdx = startIdx + searchTerm.length;
                const before = suggestionText.substring(0, startIdx);
                const matchText = suggestionText.substring(startIdx, endIdx);
                const after = suggestionText.substring(endIdx);
                
                suggestionItem.innerHTML = `
                    ${before}<span class="search-suggestion-highlight">${matchText}</span>${after}
                `;
                
                suggestionItem.addEventListener('click', () => {
                    searchInput.value = match.text;
                    filterTable(match.text);
                    suggestionsContainer.style.display = 'none';
                });
                
                suggestionsContainer.appendChild(suggestionItem);
            });
            
            suggestionsContainer.style.display = 'block';
        } else {
            suggestionsContainer.style.display = 'none';
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    makeTablesSortable();
    setupLiveSearch();
});