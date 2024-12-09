document.addEventListener("DOMContentLoaded", () => {
    // Simulando datos del backend
    const transactions = [
        { fecha: "2024-12-02", tipo: "DEPOSITO", monto: 200, descripcion: "Depósito inicial." },
        { fecha: "2024-12-02", tipo: "RETIRO", monto: 100, descripcion: "Retiro en efectivo." },
        { fecha: "2024-12-02", tipo: "TRANSFERENCIA", monto: 300, descripcion: "Transferencia enviada a cuenta 002." },
        { fecha: "2024-12-02", tipo: "TRANSFERENCIA", monto: 150, descripcion: "Transferencia recibida desde cuenta 001." },
    ];

    // Cargar las transacciones en la tabla
    const loadTransactions = (data) => {
        const tableBody = document.getElementById("transactionList");
        tableBody.innerHTML = ""; // Limpiar la tabla
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
    const filterTransactions = () => {
        const type = document.getElementById("transactionType").value;
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;

        let filtered = transactions;

        // Filtrar por tipo
        if (type !== "ALL") {
            filtered = filtered.filter((tx) => tx.tipo === type);
        }

        // Filtrar por rango de fechas
        if (startDate) {
            filtered = filtered.filter((tx) => new Date(tx.fecha) >= new Date(startDate));
        }
        if (endDate) {
            filtered = filtered.filter((tx) => new Date(tx.fecha) <= new Date(endDate));
        }

        loadTransactions(filtered);
    };

    // Inicializar
    document.getElementById("filterBtn").addEventListener("click", filterTransactions);

    // Cargar las transacciones iniciales
    loadTransactions(transactions);
});
