// Cargar las transacciones simuladas desde "el backend"
const fetchTransactions = async () => {
    try {
        // Simular una espera para datos (usamos un archivo local por ahora)
        const response = await fetch('js/data.js');
        const script = await response.text();
        eval(script);
        return backendData.transacciones;
    } catch (error) {
        console.error("Error al obtener las transacciones del backend:", error);
        return [];
    }
};

// Cargar las transacciones en la tabla
const loadTransactions = (data) => {
    const tableBody = document.getElementById("transactionList");
    tableBody.innerHTML = "";
    data.forEach((tx) => {
        const row = `
            <tr>
                <td>${tx.fecha}</td>
                <td>${tx.tipo}</td>
                <td>$${tx.monto}</td>
                <td>${tx.descripcion}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
};

// Filtrar transacciones
const filterTransactions = async () => {
    const type = document.getElementById("transactionType").value;
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;

    let transactions = await fetchTransactions();

    // Filtrar por tipo
    if (type !== "ALL") {
        transactions = transactions.filter((tx) => tx.tipo === type);
    }

    // Filtrar por rango de fechas
    if (startDate) {
        transactions = transactions.filter((tx) => new Date(tx.fecha) >= new Date(startDate));
    }
    if (endDate) {
        transactions = transactions.filter((tx) => new Date(tx.fecha) <= new Date(endDate));
    }

    loadTransactions(transactions);
};

// Inicializar
document.addEventListener("DOMContentLoaded", async () => {
    const transactions = await fetchTransactions();
    loadTransactions(transactions);

    document.getElementById("filterBtn").addEventListener("click", filterTransactions);
});
