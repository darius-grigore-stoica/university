let currentSlide = 0;
let slides = $('.slide');
let slideInterval;
let duration = parseInt($('#slideDuration').val());

slides.each(function(index){
    $('#slideCount').append(`<option value="${index}">${index + 1}</option>`);
});

function showSlide(nextSlide){
    if(nextSlide === currentSlide) return;

    slides.eq(currentSlide).removeClass('active').addClass('out');
    slides.eq(nextSlide).addClass('active');

    setTimeout(() => {
        slides.eq(currentSlide).removeClass('out');
        currentSlide = nextSlide;
        $('#slideCount').val(currentSlide);
    }, 2000); // cât durează tranziția (2 secunde)
}

function nextSlide(){
    let next = (currentSlide + 1) % slides.length;
    showSlide(next);
}

function startSlider(){
    clearInterval(slideInterval);
    slideInterval = setInterval(nextSlide, duration);
}

$('#slideDuration').on('change', function(){
    duration = parseInt($(this).val());
    startSlider();
});

$('#slideCount').on('change', function(){
    let index = parseInt($(this).val());
    showSlide(index);
});

startSlider();
