import './App.css';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListStudentComponent from './components/ListStudentComponent';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import StudentComponent from './components/StudentComponent';
import GroupChoice from './components/GroupChoice';
import LocationChoice from './components/LocationChoice';
import TeacherList from './components/TeacherList';
import { AuthProvider } from './AuthContext';
import PrivateRoute from './PrivateRoute';
import Login from './components/Login';
import axios from 'axios';

function App() {

  axios.defaults.withCredentials = true;

  return (
    <div className="app-wrapper">
      <BrowserRouter>
        <AuthProvider>
          <HeaderComponent />
          <div className='main-content'>
            <Routes>
              {/* Public Route */}
              <Route path="/login" element={<Login />} />
              <Route path="/" element={<Navigate to="/login" replace />} />

              {/* Protected Routes */}
              <Route element={<PrivateRoute />}>
                <Route path="/" element={<LocationChoice />} />
                <Route path="/students" element={<ListStudentComponent />} />
                <Route path="/teachers" element={<TeacherList />} />
                <Route path="/add-student" element={<StudentComponent />} />
                <Route path="/edit-student/:id" element={<StudentComponent />} />
                <Route path="/locations" element={<LocationChoice />} />
                <Route path="/locations/:name/groups" element={<GroupChoice />} />
                <Route path="/locations/:name_location/students" element={<ListStudentComponent />} />
                <Route path="/locations/:name_location/groups/:name_group/students" element={<ListStudentComponent />} />
              </Route>
            </Routes>
          </div>
          <FooterComponent />
        </AuthProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
