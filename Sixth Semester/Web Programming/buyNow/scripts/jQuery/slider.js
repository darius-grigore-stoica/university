$(document).ready(function() {
    let currentSlide = 0;
    let slides = $('.slide');
    let slidesPerView = 3;
    let slideInterval;
    let duration = parseInt($('#slideDuration').val());

    slides.each(function(index) {
        $('#slideCount').append(`<option value="${index}">Slide ${index + 1}</option>`);
        
        if ($(this).hasClass('video-slide')) { 
            $(this).append('<button class="video-pause">⏸</button>');
            
            if ($(this).hasClass('active')) {
                $(this).find('.slide-video')[0].play();
            }
        }
    });

    function showSlide(nextSlide){
        if(nextSlide === currentSlide) return;

        const currentVideo = slides.eq(currentSlide).find('.slide-video')[0];
        if (currentVideo) currentVideo.pause();

        const nextVideo = slides.eq(nextSlide).find('.slide-video')[0];
        if (nextVideo) nextVideo.play();

        slides.eq(currentSlide).removeClass('active').addClass('out');
        slides.eq(nextSlide).addClass('active');

        setTimeout(() => {
            slides.eq(currentSlide).removeClass('out');
            currentSlide = nextSlide;
            $('#slideCount').val(currentSlide);
        }, 2000);
    }

    $(document).on('click', '.video-pause', function() {
        const video = $(this).siblings('.slide-video')[0];
        if (video.paused) {
            video.play();
            $(this).html('⏸');
        } else {
            video.pause();
            $(this).html('▶');
        }
    });

    function nextSlide(){
        let next = (currentSlide + 1) % slidesPerView;
        showSlide(next);
    }

    function prevSlide(){
        let prev = (currentSlide - 1) % slidesPerView;
        showSlide(prev);
    }

    function startSlider(){
        clearInterval(slideInterval);
        slideInterval = setInterval(nextSlide, duration);
    }

    $('#slideDuration').on('change', function(){
        duration = parseInt($(this).val());
        startSlider();
    });

    $('.next-slide').on('click', function(e){
        e.stopPropagation();
        nextSlide();
        startSlider();
    });

    $('.prev-slide').on('click', function(e){
        e.stopPropagation();
        prevSlide();
        startSlider();
    });

    $('#slidesPerView').on('change', function() {
        slidesPerView = parseInt($(this).val());
        $('.slider').attr('data-slides-per-view', slidesPerView);
    });

    $('.slider').hover(
        function() {
            clearInterval(slideInterval);
        },
        function() {
            startSlider();
        }
    );
    
    startSlider();
});