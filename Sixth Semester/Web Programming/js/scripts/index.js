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

    const items = document.querySelectorAll('.progressive-list li');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    let currentIndex = 0;
    let intervalTimeList = 3000; // 3 secunde
    let intervalId;

    function showItem(index) {
        items.forEach(item => item.classList.remove('active'));
        
        items[index].classList.add('active');
        currentIndex = index;
    }

    function nextItem() {
        const nextIndex = (currentIndex + 1) % items.length;
        showItem(nextIndex);
    }

    function prevItem() {
        const prevIndex = (currentIndex - 1 + items.length) % items.length;
        showItem(prevIndex);
    }

    function startInterval() {
        intervalId = setInterval(nextItem, intervalTimeList);
    }

    nextBtn.addEventListener('click', function() {
        clearInterval(intervalId);
        nextItem();
        startInterval();
    });

    prevBtn.addEventListener('click', function() {
        clearInterval(intervalId);
        prevItem();
        startInterval();
    });

    startInterval();
});