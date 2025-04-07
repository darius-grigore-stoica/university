document.addEventListener('DOMContentLoaded', function() {
    const slides = document.querySelectorAll('.slide');
    const thumbnails = document.querySelectorAll('.thumbnails img');
    const playPauseBtn = document.getElementById('playPauseBtn');
    const loopCheckbox = document.getElementById('loopCheckbox');
    const intervalSelect = document.getElementById('intervalSelect');
    const prevSlideBtn = document.getElementById('prevBtn');
    const nextSlideBtn = document.getElementById('nextBtn');

    
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

        prevSlideBtn.addEventListener('click', prevSlide);
        nextSlideBtn.addEventListener('click', nextSlide);
    }

    function prevSlide() {
        let prev = currentSlide - 1;
        
        if (prev < 0) {
            if (loopCheckbox.checked) {
                prev = slides.length - 1;
            } else {
                togglePlayPause();
                return;
            }
        }
        
        showSlide(prev);
        
        if (isPlaying) {
            restartSlideShow();
        }
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
        
        if (isPlaying) {
            restartSlideShow();
        }
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
});