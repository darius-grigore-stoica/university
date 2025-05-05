$(document).ready(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    const products = [
        { id: 1, name: "BMW 320i", fairPrice: 800, currentBid: 500, imageUrl: "./images/bmw_320i.jpg", description: "BMW model 320i motor pe benzină an 2015" },
        { id: 2, name: "Frigider Bosch", fairPrice: 1200, currentBid: 800, imageUrl: "./images/frigider_bosch.jpg", description: "Frigider Bosch, 300L, clasa energetică A++" },
        { id: 3, name: "PlayStation 5", fairPrice: 2500, currentBid: 2000, imageUrl: "./images/playstation5.jpg", description: "PlayStation 5, nouă, în cutie originală" }
    ];

    const product = products.find(p => p.id == productId);
    $('#product-name').text(product.name);
    $('#product-description').text(product.description);
    $('#product-image').attr('src', product.imageUrl);
    $('#current-price').text(`${product.currentBid} RON`);
    $('#fair-price').text(`${product.fairPrice} RON`);

    let currentPrice = product.currentBid;
    let fairPrice = product.fairPrice;

    function updateGauge() {
        const percentage = (currentPrice / fairPrice);
        const angle = (percentage - 0.5) * 180 + 90; 

        $('.gauge-arrow').css('transform', 'rotate(' + angle + 'deg)');
        
        if (currentPrice > fairPrice) {
            $('.circle-bg').css('stroke', '#db2e2e');
            $('.gauge-arrow').removeClass('green').addClass('red'); 
        } else {
            $('.circle-bg').css('stroke', '#4CAF50');
            $('.gauge-arrow').removeClass('red').addClass('green');
        }
    
        $('#current-price').text(currentPrice + " RON");
    }
    

    updateGauge();

    $('#place-bid').on('click', function() {
        const userBid = parseInt($('#bid-amount').val());
        
        if (isNaN(userBid) || userBid <= currentPrice) {
            alert("Please enter a valid bid greater than the current price.");
        } else {
            currentPrice = userBid;
            updateGauge();
        }
        $('#bid-amount').val('');
    });
});
