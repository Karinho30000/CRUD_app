import { useEffect, useState } from 'react';
import NavigationButtons from './NavigationButtons';

export default function TeacherList() {
  const [teachers, setTeachers] = useState([]);
  const [students, setStudents] = useState([]);
    
  useEffect(() => {
    fetch(`${import.meta.env.VITE_API_BASE_URL}/api/students`, {credentials:'include'})
        .then((res) => res.json())
        .then(setStudents);
    }, []);


  useEffect(() => {
    fetch(`${import.meta.env.VITE_API_BASE_URL}/api/teachers`, {credentials:'include'})
      .then(res => res.json())
      .then(data => setTeachers(data));
  }, []);
  

  return (
  <div className="grid gap-4 p-4">
    <NavigationButtons/>
    <h2 className="text-xl font-bold">All Teachers</h2>

    {teachers.map((teacher) => {
      // Filter students by teacher name
      const studentCount = students.filter(
        (student) => student.teacher === teacher.name
      ).length;

      return (
        <div key={teacher.id} className="p-4 shadow-md border rounded-xl">
          <h3 className="text-lg font-semibold">{teacher.name}</h3>

          <p className="mt-2 font-medium">Groups:</p>
          <ul className="list-disc pl-5">
            {teacher.groups.map((group) => {
              const groupStudentCount = students.filter(
                (student) => 
                  student.teacher === teacher.name && student.group === group.name
              ).length;
            return (
              <li key={group.id}>
                {group.name} ({groupStudentCount} students)
              </li>
            )})}
          </ul>

          <p className="mt-4 font-semibold">
            Total students taught: {studentCount}
          </p>
        </div>
      );
    })}
  </div>
);
}
