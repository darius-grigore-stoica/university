document.addEventListener('DOMContentLoaded', function() {
    const slides = document.querySelectorAll('.slide');
    const thumbnails = document.querySelectorAll('.thumbnails img');
    const playPauseBtn = document.getElementById('playPauseBtn');
    const loopCheckbox = document.getElementById('loopCheckbox');
    const intervalSelect = document.getElementById('intervalSelect');
    
    let currentSlide = 0;
    let slideInterval;
    let isPlaying = true;
    let intervalTime = 3000;
    
    function initSlider() {
        showSlide(currentSlide);
        startSlideShow();
        
        thumbnails.forEach(thumbnail => {
            thumbnail.addEventListener('click', function() {
                const index = parseInt(this.getAttribute('data-index'));
                goToSlide(index);
            });
        });
        
        playPauseBtn.addEventListener('click', togglePlayPause);
        
        intervalSelect.addEventListener('change', function() {
            intervalTime = parseInt(this.value) * 1000;
            if (isPlaying) {
                restartSlideShow();
            }
        });
    }
    
    function showSlide(index) {
        slides.forEach(slide => {
            slide.style.display = 'none';
        });
        
        slides[index].style.display = 'block';
        currentSlide = index;
        
        thumbnails.forEach((thumb, i) => {
            thumb.style.opacity = i === index ? '1' : '0.7';
        });
    }
    
    function goToSlide(index) {
        showSlide(index);
        if (isPlaying) {
            restartSlideShow();
        }
    }
    
    function nextSlide() {
        let next = currentSlide + 1;
        
        if (next >= slides.length) {
            if (loopCheckbox.checked) {
                next = 0;
            } else {
                togglePlayPause();
                return;
            }
        }
        
        showSlide(next);
    }
    
    function startSlideShow() {
        slideInterval = setInterval(nextSlide, intervalTime);
        isPlaying = true;
        playPauseBtn.innerHTML = '<i class="fas fa-pause"></i> Pauză';
    }
    
    function stopSlideShow() {
        clearInterval(slideInterval);
        isPlaying = false;
        playPauseBtn.innerHTML = '<i class="fas fa-play"></i> Play';
    }
    
    function restartSlideShow() {
        stopSlideShow();
        startSlideShow();
    }
    
    function togglePlayPause() {
        if (isPlaying) {
            stopSlideShow();
        } else {
            startSlideShow();
        }
    }
    
    initSlider();


    let listIntervalTime = 5000; // 5 seconds
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