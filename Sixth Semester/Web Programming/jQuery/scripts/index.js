document.addEventListener('DOMContentLoaded', function() {
    let listIntervalTime = 5000;
    const listItems = document.querySelectorAll('.progressive-list li');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    let currentItem = 0;
    let listInterval;
    
    function initProgressiveList() {
        showListItem(currentItem);
        startListInterval();
        
        nextBtn.addEventListener('click', () => {
            nextListItem();
            restartListInterval();
        });
        
        prevBtn.addEventListener('click', () => {
            prevListItem();
            restartListInterval();
        });
    }
    
    function showListItem(index) {
        listItems.forEach(item => item.classList.remove('active'));
        listItems[index].classList.add('active');
        currentItem = index;
    }
    
    function nextListItem() {
        let next = currentItem + 1;
        if (next >= listItems.length) {
            next = 0;
        }
        showListItem(next);
    }
    
    function prevListItem() {
        let prev = currentItem - 1;
        if (prev < 0) {
            prev = listItems.length - 1;
        }
        showListItem(prev);
    }
    
    function startListInterval() {
        listInterval = setInterval(nextListItem, listIntervalTime);
    }
    
    function restartListInterval() {
        clearInterval(listInterval);
        startListInterval();
    }
    
    initProgressiveList();
});
