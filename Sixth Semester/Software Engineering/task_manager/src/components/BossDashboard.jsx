import React, { useState } from 'react';
import EmployeeList from './EmployeeList';
import TaskForm from './TaskForm';

const BossDashboard = () => {
  // Mock data
  const [employees, setEmployees] = useState([
    { id: 1, name: 'John Doe', online: true },
    { id: 2, name: 'Jane Smith', online: true },
    { id: 3, name: 'Bob Johnson', online: false },
  ]);

  const [showTaskForm, setShowTaskForm] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  const handleAssignTask = (employeeId) => {
    setSelectedEmployee(employeeId);
    setShowTaskForm(true);
  };

  const handleSubmitTask = (taskData) => {
    // In a real app, you would send this to the backend
    console.log(`Assigning task to employee ${selectedEmployee}:`, taskData);
    setShowTaskForm(false);
  };

  return (
    <div className="dashboard">
      <h2>Employee Management</h2>
      <EmployeeList
        employees={employees}
        onAssignTask={handleAssignTask}
      />
      
      {showTaskForm && (
        <TaskForm
          onSubmit={handleSubmitTask}
          onCancel={() => setShowTaskForm(false)}
        />
      )}
    </div>
  );
};

export default BossDashboard;