function stergeFavorite(idAnunt) {
    fetch("api/favorite/delete.php", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id_anunt: idAnunt })
    })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            const element = document.getElementById("anunt-" + idAnunt);
            if (element) element.remove();
        } else {
            alert("Eroare: " + result.message);
        }
    })
    .catch(() => alert("Eroare la comunicarea cu serverul."));
}