import React from 'react';

const EmployeeList = ({ employees, onAssignTask }) => {
  const onlineEmployees = employees.filter((e) => e.status === 'ONLINE');

  return (
    <div className="employee-list">
      <h3>Online Employees</h3>
      {onlineEmployees.length === 0 ? (
        <p>No employees currently online.</p>
      ) : (
        <ul>
          {onlineEmployees.map((employee) => (
            <li key={employee.id} className="employee-item">
              <span>{employee.username}</span>
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
