import { useEffect, useState } from "react";
import api from "../api/api";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const [message, setMessage] = useState("");
  const { token, logout } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate("/");
      return;
    }

    api.get("/api/hello")
      .then((res) => setMessage(res.data))
      .catch(() => logout());
  }, [token]);

  return (
    <div>
      <h2>Área Protegida</h2>
      <p>{message}</p>
      <button onClick={logout}>Sair</button>
    </div>
  );
};

export default Home;
