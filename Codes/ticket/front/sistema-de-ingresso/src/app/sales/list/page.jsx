'use client';

import axios from 'axios';
import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';

const formatCurrency = (value) => {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value);
};

export default function ListaDeVendas() {
  const router = useRouter();

  const [allSales, setAllSales] = useState([]);
  const [filteredSales, setFilteredSales] = useState([]);
  const [statusFilter, setStatusFilter] = useState('TODOS');

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSalesData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/sales');

        if (response.status !== 200) {
            throw new Error(`Erro ao buscar dados: ${response.statusText}`);
        }

        const data = response.data;
        setAllSales(data);
        setFilteredSales(data);
        setError(null);
      } catch (err) {
        console.error("Falha ao buscar dados da API:", err);
        setError("Não foi possível carregar os dados das vendas. Por favor, tente novamente mais tarde.");
      } finally {
        setLoading(false);
      }
    };

    fetchSalesData();
  }, []);


  useEffect(() => {
    if (statusFilter === 'TODOS') {
      setFilteredSales(allSales);
    } else {
      const filtered = allSales.filter(sale => sale.salesDetailDTO.purchaseStatus === statusFilter);
      setFilteredSales(filtered);
    }
  }, [statusFilter, allSales]);

  const getStatusBadge = (status) => {
    switch (status) {
      case 'PAGO': return 'bg-success';
      case 'ESTORNADO': return 'bg-info';
      case 'CANCELADO': return 'bg-danger';
      case 'EMABERTO': return 'bg-warning text-dark';
      default: return 'bg-secondary';
    }
  };
  

  const handleEditClick = (saleId) => {
    router.push(`/sales/update/${saleId}`);
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
      <h2 className="mb-4">Lista de Vendas</h2>

      <div className="row mb-3">
        <div className="col-md-4">
          <label htmlFor="statusFilter" className="form-label">Filtrar por Status</label>
          <select
            id="statusFilter"
            className="form-select"
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value)}
          >
            <option value="TODOS">Todos</option>
            <option value="EMABERTO">Em Aberto</option>
            <option value="PAGO">Pago</option>
            <option value="CANCELADO">Cancelado</option>
            <option value="ESTORNADO">Estornado</option>
          </select>
        </div>
      </div>

      <div className="table-responsive">
        <table className="table table-striped table-hover align-middle">
          <thead className="table-dark">
            <tr>
              <th>Data da Compra</th>

              <th>Comprador</th>
              <th>Evento</th>
              <th>Status</th>
              <th>Valor do Ingresso</th>
              <th>Detalhes do Evento</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {filteredSales.length > 0 ? (
              filteredSales.map((sale) => (

                <tr key={sale.salesDetailDTO.saleId}>

                  <td>{sale.salesDetailDTO.purchaseDate || 'N/A'}</td>
                  

                  <td>{sale.simplesUserDTO?.name || 'N/A'}</td>

                  <td>{sale.salesDetailDTO.eventDescription}</td>
                  <td>
                    <span className={`badge ${getStatusBadge(sale.salesDetailDTO.purchaseStatus)}`}>
                      {sale.salesDetailDTO.purchaseStatus}
                    </span>
                  </td>
                  <td>{formatCurrency(sale.salesDetailDTO.eventPrice)}</td>
                  <td>
                    <small className="text-muted">
                      Data do Evento: {sale.salesDetailDTO.eventDate || 'N/A'}<br />
                      ID da Venda: {sale.salesDetailDTO.saleId}<br />
                      ID do Usuário: {sale.salesDetailDTO.userId}<br />
                    </small>
                  </td>
                  <td>
                    <button 
                      className="btn btn-primary btn-sm" 
                      onClick={() => handleEditClick(sale.salesDetailDTO.saleId)}
                    >
                      Editar status
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="7" className="text-center py-4">Nenhuma venda encontrada.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}