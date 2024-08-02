import './App.css';
import { useReducer } from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Header from './components/Header';
import PageContent from './components/PageContent';
import Footer from './components/Footer';
import postReducer from './reducers/postReducer';
import { UserProvider } from './context/UserProvider';
import RegistrationForm from './components/RegistrationForm';

function App() {
  const initialState = { posts: [] };

  const [state, dispatch] = useReducer(postReducer, initialState);

  return (
    <div className="App">
      <UserProvider>
        <Header></Header>
        <Router>
          <Routes>
            <Route
              path="/"
              element={
                <PageContent state={state} dispatch={dispatch}></PageContent>
              }
            ></Route>
            <Route path="/register" element={<RegistrationForm />}></Route>
          </Routes>
        </Router>

        <Footer></Footer>
      </UserProvider>
    </div>
  );
}

export default App;
