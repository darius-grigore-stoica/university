import React, { useState } from 'react';
import TaskList from './TaskList';

const EmployeeDashboard = () => {
  // Mock data - in a real app, this would come from an API
  const [tasks, setTasks] = useState([
    { id: 1, description: 'Complete project report', status: 'pending' },
    { id: 2, description: 'Fix login page bug', status: 'pending' },
    { id: 3, description: 'Prepare presentation', status: 'completed' },
  ]);

  const handleTaskComplete = (taskId) => {
    setTasks(
      tasks.map((task) =>
        task.id === taskId ? { ...task, status: 'completed' } : task
      )
    );
  };

  return (
    <div className="dashboard">
      <h2>Your Tasks</h2>
      <TaskList tasks={tasks} onTaskComplete={handleTaskComplete} />
    </div>
  );
};

export default EmployeeDashboard;