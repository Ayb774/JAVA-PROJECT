const apiUrl = "http://localhost:8080/api/programmeurs/";



function showDetail(programmer) {
    window.location.href = `programmeur.html?id=${programmer.id}`;
}

async function afficherDetailsProgrammeur() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("Aucun programmeur spécifié !");
        return;
    }

    try {
        const response = await fetch(`${apiUrl}${id}`);
        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }

        const programmer = await response.json();
        document.getElementById('detail-id').textContent = programmer.id;
        document.getElementById('detail-name').value = programmer.nom;
        document.getElementById('detail-firstname').value = programmer.prenom;
        document.getElementById('detail-address').value = programmer.adresse;
        document.getElementById('detail-pseudo').value = programmer.pseudo;
        document.getElementById('detail-manager').value = programmer.responsable;
        document.getElementById('detail-hobby').value = programmer.hobby;
        document.getElementById('detail-birth').value = programmer.anNaissance;
        document.getElementById('detail-salary').value = programmer.salaire;
        document.getElementById('detail-bonus').value = programmer.prime;

        const inputs = document.querySelectorAll('.editable');
        inputs.forEach(input => {
            input.addEventListener('dblclick', () => {
                input.removeAttribute('readonly');
                input.style.backgroundColor = '#292929';
            });
        });

    } catch (error) {
        console.error("Erreur lors de la récupération des détails du programmeur :", error);
    }
}


async function ajouterProgrammeur(event) {
    event.preventDefault();

    const newProgrammeur = {
        nom: document.getElementById('nom').value,
        prenom: document.getElementById('prenom').value,
        adresse: document.getElementById('adresse').value,
        pseudo: document.getElementById('pseudo').value,
        responsable: document.getElementById('responsable').value,
        hobby: document.getElementById('hobby').value,
        anNaissance: document.getElementById('naissance').value,
        salaire: parseFloat(document.getElementById('salaire').value),
        prime: parseFloat(document.getElementById('prime').value),
    };

    try {
        const response = await fetch("http://localhost:8080/api/programmeurs/", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newProgrammeur),
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }

        const notification = document.getElementById('notification');
        notification.textContent = "Programmeur ajouté avec succès !";
        notification.className = '';
        notification.classList.add('show');
        notification.style.display = 'block';

        document.querySelector('form').reset();

        setTimeout(() => {
            notification.style.display = 'none';
        }, 3000);

    } catch (error) {
        console.error("Erreur lors de l'ajout du programmeur :", error);

        const notification = document.getElementById('notification');
        notification.textContent = "Une erreur s'est produite lors de l'ajout.";
        notification.className = '';
        notification.classList.add('error');
        notification.style.display = 'block';

        setTimeout(() => {
            notification.style.display = 'none';
        }, 3000);
    }
}


async function fetchProgrammeurs() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }
        const programmeurs = await response.json();

        const list = document.getElementById('programmers-list');
        list.innerHTML = '';
        programmeurs.forEach(prog => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${prog.nom}</td>
                <td>${prog.prenom}</td>
                <td>${prog.anNaissance}</td>
                <td>${prog.salaire}</td>
            `;
            row.onclick = () => showDetail(prog);
            list.appendChild(row);
        });
    } catch (error) {
        console.error("Erreur lors de la récupération des programmeurs :", error);
    }
}
async function supprimerProgrammeur() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("Aucun programmeur spécifié !");
        return;
    }

    try {
        const response = await fetch(`${apiUrl}${id}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }

        window.location.href = "index.html";
    } catch (error) {
        console.error("Erreur lors de la suppression du programmeur :", error);
        alert("Une erreur s'est produite lors de la suppression du programmeur.");
    }
}




function filterProgrammers() {
    const query = document.getElementById('search-bar').value.toLowerCase();
    const rows = document.querySelectorAll('#programmers-list tr');

    rows.forEach(row => {
        const cells = Array.from(row.cells);
        const matches = cells.some(cell => cell.textContent.toLowerCase().includes(query));

        if (matches) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

function ouvrirFormulaire() {
    document.getElementById('employee-form').classList.remove('hidden');
}

function fermerFormulaire() {
    document.getElementById('employee-form').classList.add('hidden');
}
function allerDashboard() {
    window.location.href = "index.html";
}

async function enregistrerModifications() {
    const id = document.getElementById('detail-id').textContent;

    const updatedProgrammeur = {
        nom: document.getElementById('detail-name').value,
        prenom: document.getElementById('detail-firstname').value,
        adresse: document.getElementById('detail-address').value,
        pseudo: document.getElementById('detail-pseudo').value,
        responsable: document.getElementById('detail-manager').value,
        hobby: document.getElementById('detail-hobby').value,
        anNaissance: document.getElementById('detail-birth').value,
        salaire: parseFloat(document.getElementById('detail-salary').value),
        prime: parseFloat(document.getElementById('detail-bonus').value),
    };

    try {
        const response = await fetch(`${apiUrl}${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedProgrammeur),
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }

        const notification = document.getElementById('notification');
        notification.textContent = "Programmeur modifié avec succès !";
        notification.classList.remove('hidden');

        setTimeout(() => {
            notification.classList.add('hidden');
        }, 3000);

    } catch (error) {
        console.error("Erreur lors de la modification du programmeur :", error);
        alert("Une erreur s'est produite lors de la modification.");
    }
}






async function updateKeyMetrics() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`Erreur HTTP : ${response.status}`);
        }
        const programmeurs = await response.json();

        const totalProgrammers = programmeurs.length;
        const totalSalary = programmeurs.reduce((acc, prog) => acc + prog.salaire, 0);
        const totalBonus = programmeurs.reduce((acc, prog) => acc + prog.prime, 0);
        const averageSalary = totalProgrammers > 0 ? (totalSalary / totalProgrammers).toFixed(2) : 0;

        document.getElementById('total-programmers').textContent = totalProgrammers;
        document.getElementById('average-salary').textContent = averageSalary;
        document.getElementById('total-bonus').textContent = totalBonus;
    } catch (error) {
        console.error("Erreur lors de la mise à jour des indicateurs clés :", error);
    }
}

window.onload = fetchProgrammeurs;
