import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'https://crud-app-qi6c.onrender.com';
const REST_API_BASE_URL = `${API_BASE_URL}/api/students`;
const REST_API_GROUPS_URL = `${API_BASE_URL}/api/groups`;
const REST_API_LOCATIONS_URL = `${API_BASE_URL}/api/locations`;

export const listStudents = () => axios.get(REST_API_BASE_URL);
export const createStudent = (student) => axios.post(REST_API_BASE_URL, student);
export const getStudent = (studentId) => axios.get(`${REST_API_BASE_URL}/${studentId}`);
export const updateStudent = (studentId, student) => axios.put(`${REST_API_BASE_URL}/${studentId}`, student);
export const deleteStudent = (studentId) => axios.delete(`${REST_API_BASE_URL}/${studentId}`);

export const listGroups = () => axios.get(REST_API_GROUPS_URL);
export const listLocations = () => axios.get(REST_API_LOCATIONS_URL);
