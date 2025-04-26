const orase = {
    'Cluj': ['Cluj-Napoca', 'Dej', 'Gherla', 'Turda', 'Câmpia Turzii'],
    'Alba': ['Alba Iulia', 'Ocna Mureș', 'Aiud', 'Teiuș', 'Blaj'],
    'București': ['Sector 1', 'Sector 2', 'Sector 3', 'Sector 4', 'Sector 5', 'Sector 6'],
    'Iași': ['Iași', 'Pașcani', 'Târgu Frumos', 'Hârlău'],
    'Timiș': ['Timișoara', 'Lugoj', 'Deta', 'Jimbolia']
};

document.addEventListener('DOMContentLoaded', function() {
    const judetSelect = document.getElementById('judet');
    const orasSelect = document.getElementById('oras');

    for (const judet in orase) {
        const option = document.createElement('option');
        option.value = judet;
        option.textContent = judet;
        judetSelect.appendChild(option);
    }

    judetSelect.addEventListener('change', function() {
        orasSelect.innerHTML = '';
        orasSelect.disabled = !this.value;

        if (this.value) {
            const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.textContent = 'Selectează orașul';
            orasSelect.appendChild(defaultOption);

            // Populăm cu orașele din județul selectat
            orase[this.value].forEach(oras => {
                const option = document.createElement('option');
                option.value = oras;
                option.textContent = oras;
                orasSelect.appendChild(option);
            });

            orasSelect.disabled = false;
        } else {
            // Dacă nu este selectat niciun județ
            const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.textContent = 'Selectează județul mai întâi';
            orasSelect.appendChild(defaultOption);
            orasSelect.disabled = true;
        }
    });

    judetSelect.dispatchEvent(new Event('change'));
});
