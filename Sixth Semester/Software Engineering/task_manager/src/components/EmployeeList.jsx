import React from 'react';

const EmployeeList = ({ employees, onAssignTask }) => {
  return (
    <div className="employee-list">
      <h3>Online Employees</h3>
      {employees.filter(e => e.online).length === 0 ? (
        <p>No employees currently online.</p>
      ) : (
        <ul>
          {employees
            .filter((employee) => employee.online)
            .map((employee) => (
              <li key={employee.id} className="employee-item">
                <span>{employee.name}</span>
                <button
                  onClick={() => onAssignTask(employee.id)}
                  className="assign-button"
                >
                  Assign Task
                </button>
              </li>
            ))}
        </ul>
      )}
    </div>
  );
};

export default EmployeeList;