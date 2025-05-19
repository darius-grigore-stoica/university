document.addEventListener('DOMContentLoaded', function () {
    // const listContainer = document.getElementById("categorieList");

    // fetch("api/categorii/read.php")
    //     .then(response => response.json())
    //     .then(categorii => {
    //         listContainer.innerHTML = "";

    //         categorii.forEach(cat => {
    //             const li = document.createElement("li");
    //             li.innerHTML = `<a href="anunturi.html?categorie=${cat.id}">${cat.nume}</a>`;
    //             listContainer.appendChild(li);
    //         });
    //     })
    //     .catch(error => {
    //         console.error("Eroare la încărcarea categoriilor:", error);
    //     });

    // const form = document.getElementById("loginForm");

    // if (!form) {
    //     console.log("Formularul nu a fost găsit!");
    //     return;
    // }

    // form.addEventListener("submit", async function (e) {
    //     e.preventDefault();

    //     const username = document.getElementById("username").value;
    //     const parola = document.getElementById("password").value;

    //     const response = await fetch("api/login.php", {
    //         method: "POST",
    //         headers: { "Content-Type": "application/json" },
    //         body: JSON.stringify({ username, parola })
    //     });

    //     const result = await response.json();

    //     if (result.success) {
    //         if (result.rol === "cumparator") {
    //             window.location.href = "anunturi.html";
    //         } else {
    //             window.location.href = "adauga_anunt.php";
    //         }
    //     } else {
    //         alert(result.message);
    //     }
    // });
});
