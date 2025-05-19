import React, { useEffect, useState } from 'react';
import TaskList from './TaskList';

const EmployeeDashboard = ({ employeeId, username }) => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const res = await fetch(`http://localhost:8080/api/employees/${employeeId}/tasks`);
        if (!res.ok) throw new Error('Failed to fetch tasks');
        const data = await res.json();
        setTasks(data);
      } catch (err) {
        console.error('Error fetching tasks:', err);
        setError('Could not load tasks.');
      }
    };

    fetchTasks();
  }, [employeeId]);

  const handleTaskComplete = async (taskId) => {
    try {
      const res = await fetch(
        `http://localhost:8080/api/employees/${employeeId}/tasks/${taskId}/complete`,
        {
          method: 'POST',
        }
      );
      if (!res.ok) throw new Error('Failed to complete task');

      // Update the task status locally
      setTasks(
        tasks.map((task) =>
          task.id === taskId ? { ...task, status: 'completed' } : task
        )
      );
    } catch (err) {
      console.error('Error completing task:', err);
      alert('Could not mark task as completed.');
    }
  };

  return (
    <div className="dashboard">
      <h2>Welcome, {username}</h2>
      <h3>Your Tasks</h3>
      {error && <p className="error-message">{error}</p>}
      <TaskList tasks={tasks} onTaskComplete={handleTaskComplete} />
    </div>
  );
};

export default EmployeeDashboard;
