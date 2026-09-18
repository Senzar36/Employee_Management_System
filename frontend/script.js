function processAddition() {
    const name = document.getElementById('name').value;
    const id = parseInt(document.getElementById('id').value);
    const dept = document.getElementById('department').value;
    const desig = document.getElementById('designation').value;
    const sal = parseFloat(document.getElementById('salary').value);
    const age = parseInt(document.getElementById('age').value);

    if (window.javaConnector) {
        window.javaConnector.addEmployeeUI(name, id, dept, desig, sal, age);
        alert("Employee Added Successfully!");
        document.getElementById('addEmployeeForm').reset();
    }
}

function processUpdate() {
    const id = parseInt(document.getElementById('id').value);
    const name = document.getElementById('name').value;
    const age = parseInt(document.getElementById('age').value);
    const dept = document.getElementById('department').value;
    const desig = document.getElementById('designation').value;
    const sal = parseFloat(document.getElementById('salary').value);
    if (window.javaConnector) {
        window.javaConnector.updateEmployeeUI(id, name, age, dept, desig, sal);
        alert("Employee Updated Successfully!");
        document.getElementById('updateEmployeeForm').reset();
    }
}

function processDeletion() {
    const id = parseInt(document.getElementById('id').value);
    if (window.javaConnector) {
        window.javaConnector.deleteEmployeeUI(id);
        alert("Employee Deleted!");
        document.getElementById('deleteEmployeeForm').reset();
    }
}

function processSearch() {
    const id = parseInt(document.getElementById('searchTerm').value);
    const resultDiv = document.getElementById('searchResult');
    if (window.javaConnector) {
        const result = window.javaConnector.searchEmployeeUI(id);
    if (result) {
        const emp = JSON.parse(result);
        resultDiv.innerHTML = `<div style="border:1px solid white;padding:
        10px;margin-top:10px;">
        <p><strong>Name:</strong> ${emp.name}</p>
        <p><strong>Dept:</strong> ${emp.department}</p>
        <p><strong>Salary:</strong> ${emp.salary}</p></div>`;

    } else {
        resultDiv.innerHTML = "<p>Employee not found.</p>";
    }
    }
}

window.onload = function() {
    const tableBody = document.getElementById("employeeTableBody");
    if (tableBody) {
        setTimeout(() => {
    if (window.javaConnector) {
        const employees = JSON.parse(window.javaConnector.getEmployeesJSON());
        tableBody.innerHTML = employees.map(emp => `
        <tr>
        <td>${emp.id}</td>
        <td>${emp.name}</td>
        <td>${emp.age}</td>
        <td>${emp.department}</td>
        <td>${emp.designation}</td>
        <td>${emp.salary}</td>
        </tr>`).join('');
    }
        }, 100);
    }
};