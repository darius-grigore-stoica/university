$(document).ready(function() {
    const $searchInput = $('#searchInput');
    const $suggestionsContainer = $('#searchSuggestions');
    const $table = $('.anunturi-table');
    const $rows = $table.find('tbody tr');
    
    const allItems = $rows.map(function() {
        const $cells = $(this).find('td');
        return {
            element: this,
            text: $cells.slice(1, -1).map(function() {
                return $(this).text().toLowerCase();
            }).get().join(' ')
        };
    }).get();

    $searchInput.on('input', function() {
        const searchTerm = $(this).val().toLowerCase();
        
        if (searchTerm === '') {
            resetTable();
        } else {
            showSuggestions(searchTerm);
        }
    });
    
    $(document).on('click', function(e) {
        if (!$(e.target).closest('.search-autocomplete').length) {
            $suggestionsContainer.hide();
            if ($searchInput.val() === '') {
                resetTable();
            }
        }
    });
    
    function showSuggestions(searchTerm) {
        if (searchTerm.length < 2) {
            $suggestionsContainer.hide();
            return;
        }
        
        const matches = allItems.filter(item => 
            item.text.includes(searchTerm) && item.text !== searchTerm
        );
        
        $suggestionsContainer.empty();
        
        if (matches.length > 0) {
            matches.slice(0, 5).forEach(function(match) {
                const suggestionText = match.text.length > 50 ? match.text.substring(0, 50) + '...' : match.text;
                
                const startIdx = match.text.indexOf(searchTerm);
                const endIdx = startIdx + searchTerm.length;
                const before = suggestionText.substring(0, startIdx);
                const matchText = suggestionText.substring(startIdx, endIdx);
                const after = suggestionText.substring(endIdx);
                
                const $suggestionItem = $('<div>').addClass('search-suggestion-item')
                    .html(`${before}<span class="search-suggestion-highlight">${matchText}</span>${after}`)
                    .on('click', function() {
                        $searchInput.val(match.text);
                        filterTable(match.text);  // Filter the table based on the selected suggestion
                        $suggestionsContainer.hide();  // Hide suggestions
                    });
                
                $suggestionsContainer.append($suggestionItem);
            });
            
            $suggestionsContainer.show();
        } else {
            $suggestionsContainer.hide();
        }
    }
    
    function filterTable(searchTerm) {
        $rows.each(function() {
            const $row = $(this);
            const match = allItems.find(item => item.element === this);
            if (match.text.includes(searchTerm)) {
                $row.show();
            } else {
                $row.hide();
            }
        });
    }

    function resetTable() {
        $rows.each(function() {
            $(this).show();
        });
    }
});
