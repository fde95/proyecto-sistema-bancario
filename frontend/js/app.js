// Formatear fechas de yyyy-mm-dd a dd/mm/yyyy
const formatDate = (dateString) => {
    const [year, month, day] = dateString.split("-");
    return `${day}/${month}/${year}`;
};

// Cargar las transacciones simuladas desde "el backend"
const fetchTransactions = async () => {
    try {
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
                <td>${formatDate(tx.fecha)}</td>
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

    if (type !== "ALL") {
        transactions = transactions.filter((tx) => tx.tipo === type);
    }

    if (startDate) {
        transactions = transactions.filter((tx) => new Date(tx.fecha) >= new Date(startDate));
    }

    if (endDate) {
        transactions = transactions.filter((tx) => new Date(tx.fecha) <= new Date(endDate));
    }

    loadTransactions(transactions);
};

// Validar el rango de fechas
const validateDateRange = () => {
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");

    const startDate = new Date(startDateInput.value);
    const endDate = new Date(endDateInput.value);

    // Si la fecha de inicio es mayor que la de fin, ajustar
    if (startDate > endDate) {
        endDateInput.value = startDateInput.value;
    }

    // Si la fecha de fin es menor que la de inicio, ajustar
    if (endDate < startDate) {
        startDateInput.value = endDateInput.value;
    }
};

// Inicializar
document.addEventListener("DOMContentLoaded", async () => {
    const transactions = await fetchTransactions();
    loadTransactions(transactions);

    document.getElementById("filterBtn").addEventListener("click", filterTransactions);

    // Validar fechas en tiempo real
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");
    startDateInput.addEventListener("input", validateDateRange);
    endDateInput.addEventListener("input", validateDateRange);
});
