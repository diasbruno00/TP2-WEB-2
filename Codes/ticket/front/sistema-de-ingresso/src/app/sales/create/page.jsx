'use client';

import axios from 'axios';
import { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import { useRouter } from 'next/navigation';

export default function CadastroDeVendas() {
 
  const [formData, setFormData] = useState({
    user_id: '',
    event_id: '',
    purchaseDate: '',
    purchaseStatus: 'EMABERTO', // Atualizado para refletir o Enum correto
  });

  const [users, setUsers] = useState([]);
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const router = useRouter();

  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        const [usersResponse, eventsResponse] = await Promise.all([
          axios.get('http://localhost:8080/users'),
          axios.get('http://localhost:8080/event'),
        ]);

        if (usersResponse.status !== 200 || eventsResponse.status !== 200) {
          throw new Error('Falha ao buscar dados iniciais.');
        }

        setUsers(usersResponse.data);
        setEvents(eventsResponse.data);
        setError(null);
      } catch (err) {
        console.error("Erro ao buscar dados:", err);
        setError("Não foi possível carregar os usuários ou eventos. Tente novamente.");
      } finally {
        setLoading(false);
      }
    };

    fetchInitialData();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.user_id || !formData.event_id) {
      Swal.fire({
        title: 'Erro!',
        text: 'Por favor, preencha todos os campos obrigatórios.',
        icon: 'error',
        confirmButtonText: 'OK'
      });
      return;
    }
    
    console.log('Dados do formulário para enviar:', formData);

    try {
      const response = await axios.post('http://localhost:8080/sales', formData);
      if (response.status === 201 || response.status === 200) {
        Swal.fire({
          title: 'Sucesso!',
          text: 'Venda registrada com sucesso.',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        console.log('Venda registrada:', response.data);
        // Redireciona para a lista de vendas após salvar
        router.push('/sales/list');
      } else {
        Swal.fire({
          title: 'Erro!',
          text: `Erro ao registrar venda: ${response.data.message}`,
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    } catch(err) {
      console.error("Falha ao registrar venda:", err);
      alert(`Erro: ${err.response?.data?.message || err.message}`);
    }
  };

  if (loading) {
    return (
      <div className="d-flex justify-content-center align-items-center" style={{ height: '50vh' }}>
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Carregando...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mt-5">
        <div className="alert alert-danger" role="alert">
          <strong>Erro:</strong> {error}
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8 col-lg-6">
          <div className="card shadow-sm">
            <div className="card-body p-4">
              <h2 className="card-title text-center mb-4">Registrar Nova Venda</h2>
              <form onSubmit={handleSubmit}>
                
                <div className="mb-3">
                  <label htmlFor="user_id" className="form-label">Usuário</label>
                  <select
                    className="form-select"
                    id="user_id"
                    name="user_id" 
                    value={formData.user_id}
                    onChange={handleChange}
                    required
                  >
                    <option value="" disabled>Selecione um usuário...</option>
                    {users.map(user => (
                      <option key={user.id} value={user.id}>{user.name}</option>
                    ))}
                  </select>
                </div>
                
                <div className="mb-3">
                  <label htmlFor="event_id" className="form-label">Evento</label>
                  <select
                    className="form-select"
                    id="event_id"
                    name="event_id" 
                    value={formData.event_id}
                    onChange={handleChange}
                    required
                  >
                    <option value="" disabled>Selecione um evento...</option>
                    {events.map(event => (
                      <option key={event.id} value={event.id}>{event.description}</option>
                    ))}
                  </select>
                </div>

                <div className="mb-3">
                  <label htmlFor="purchaseDate" className="form-label">Data da Venda</label>
                  <input
                    type="date"
                    className="form-control"
                    id="purchaseDate"
                    name="purchaseDate" 
                    value={formData.purchaseDate}
                    onChange={handleChange}
                    required
                  />
                </div>
                
                <div className="mb-3">
                  <label htmlFor="purchaseStatus" className="form-label">Status da Venda</label>
                  <select
                    className="form-select"
                    id="purchaseStatus"
                    name="purchaseStatus" // ATUALIZADO
                    value={formData.purchaseStatus}
                    onChange={handleChange}
                    required
                  >
                    {/* É melhor enviar o nome do Enum que o backend espera */}
                    <option value="EMABERTO">Em Aberto</option>
                    <option value="PAGO">Pago</option>
                    <option value="CANCELADO">Cancelado</option>
                    <option value="ESTORNADO">Estornado</option>
                  </select>
                </div>
                
                <div className="d-grid mt-4">
                  <button type="submit" className="btn btn-primary btn-lg">Salvar Venda</button>
                </div>

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}