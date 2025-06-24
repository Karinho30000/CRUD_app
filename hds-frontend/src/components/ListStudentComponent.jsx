import React, {useEffect, useState} from 'react'
import { deleteStudent, listStudents } from '../services/StudentService'
import { useNavigate, useParams } from 'react-router-dom'
import NavigationButtons from './NavigationButtons'

const ListStudentComponent = () => {

    const [students, setStudents] =  useState([])
    const { name_location, name_group } = useParams();
    const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });

    const navigator = useNavigate();

    useEffect(() => {
        if(name_location && name_group){
            getStudentsByGroup(name_group, name_location);
        }else if(name_location){
            getStudentsByLocation(name_location);
        } else{
            getAllStudents();
        }
    }, [name_group, name_location]);


    function getAllStudents(){
        listStudents().then((response) => {
            setStudents(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function getStudentsByLocation(locationName){
        fetch(`${import.meta.env.VITE_API_BASE_URL}/api/locations/${locationName}/students`, {credentials:'include'})
        .then((response) => response.json())
        .then((data) => {
            setStudents(data);
        }).catch((error) => {
            console.error(error);
        })
    }

    function getStudentsByGroup(groupName, locationName){
        fetch(`${import.meta.env.VITE_API_BASE_URL}/api/locations/${locationName}/groups/${groupName}/students`, {credentials:'include'})
            .then((response) => response.json())
            .then((data) => {
                setStudents(data);
            }).catch((error) => {
                console.error(error);
            })
    }

    function sortBy(key) {
    let direction = 'asc';
    if (sortConfig.key === key && sortConfig.direction === 'asc') {
        direction = 'desc';
    }

    const sortedStudents = [...students].sort((a, b) => {
        const valA = a[key]?.toLowerCase?.() || '';
        const valB = b[key]?.toLowerCase?.() || '';

        return direction === 'asc'
            ? valA.localeCompare(valB, 'hr')
            : valB.localeCompare(valA, 'hr');
    });

    setSortConfig({ key, direction });
    setStudents(sortedStudents);
    }


    function addNewStudent() {
        if(name_group){
                    navigator('/add-student', {
                        state: {
                            preselectedGroup: name_group,
                            preselectedLocation: name_location
                        }
                    });
        }else if(name_location){
            navigator('/add-student', {
                state: {
                    preselectedLocation: name_location
                }
            })
        }else{
                    navigator('/add-student');
                }       
    }    

    function updateStudent(id){
        navigator(`/edit-student/${id}`, {
            state: {preselectedGroup: name_group}
        })
    }

    function removeStudent(id){
        console.log(id);

        deleteStudent(id).then(() => {
           if(name_group){
                getStudentsByGroup(name_group, name_location);
            } else if(name_location){
                getStudentsByLocation(name_location);
            } else{
                getAllStudents();
            }
        }).catch(error => {
            console.error(error);
        })
    }

  return (
    <div className='container mt-4'>
        <NavigationButtons/>
        <h2 className='text-center mb-4'>
            {name_group ? `Students in group ${name_group} at location ${name_location}` : name_location ? `Students at location ${name_location}` : 'All Students'}
        </h2>
        <button type='button' className='btn-add mb-3' onClick={addNewStudent}>
            {name_group ? 'Add Student To This Group' : name_location ? 'Add Student To This Location' : 'Add New Student'}
        </button>
        
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th onClick={() => sortBy('firstName')} style={{ cursor: 'pointer' }}>
                        Student First Name {sortConfig.key === 'firstName' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                    </th>
                    <th onClick={() => sortBy('lastName')} style={{ cursor: 'pointer' }}>
                        Student Last Name {sortConfig.key === 'lastName' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                    </th>
                    <th>Group</th>
                    <th>Teacher</th>
                    <th>Location</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    students.map(student =>
                        <tr key={student.id}>
                            <td>{student.firstName}</td>
                            <td>{student.lastName}</td>
                            <td>{student.group}</td>
                            <td>{student.teacher}</td>
                            <td>{student.location}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateStudent(student.id)}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeStudent(student.id)} 
                                    style={{marginLeft: '10px'}}    
                                >Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListStudentComponent
