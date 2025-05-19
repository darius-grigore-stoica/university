import React, { useEffect, useState } from 'react';
import EmployeeList from './EmployeeList';
import TaskForm from './TaskForm';

const BossDashboard = ({ bossId, username }) => {
  const [employees, setEmployees] = useState([]);
  const [showTaskForm, setShowTaskForm] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/boss/${bossId}/online-employees`);
        if (!response.ok) throw new Error('Failed to fetch employees');
        const data = await response.json();
        setEmployees(data);
      } catch (err) {
        setError('Error fetching online employees.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployees();
  }, [bossId]);

  const handleAssignTask = (employeeId) => {
    setSelectedEmployee(employeeId);
    setShowTaskForm(true);
  };

  const handleSubmitTask = async (taskData) => {
    try {
      const response = await fetch(`http://localhost:8080/api/boss/${bossId}/assign-task`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          employeeId: selectedEmployee,
          description: taskData.description,
        }),
      });

      if (!response.ok) throw new Error('Failed to assign task');
      console.log('Task assigned successfully!');
    } catch (err) {
      console.error('Error assigning task:', err);
      alert('Could not assign task.');
    } finally {
      setShowTaskForm(false);
    }
  };

  return (
    <div className="dashboard">
      <h2>Welcome, Boss {username}</h2>
      <h3>Online Employees</h3>

      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p className="error-message">{error}</p>
      ) : (
        <EmployeeList employees={employees} onAssignTask={handleAssignTask} />
      )}

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
