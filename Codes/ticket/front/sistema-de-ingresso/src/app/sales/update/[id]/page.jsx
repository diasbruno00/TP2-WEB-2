'use client';

import { useState, useEffect } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import Swal from 'sweetalert2';

const formatarMoeda = (valor) => {
  if (typeof valor !== 'number') {
    return 'N/A';
  }
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(valor);
};


const OPCOES_STATUS = ['EMABERTO', 'PAGO', 'CANCELADO', 'ESTORNADO'];

export default function PaginaEditarVenda({ params }) {
  const router = useRouter();
  const { id } = params;


  const [venda, setVenda] = useState(null);
  const [novoStatus, setNovoStatus] = useState('');
  
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState(null);
  const [salvando, setSalvando] = useState(false);

  


  useEffect(() => {
    if (!id) return;

    const buscarDadosDaVenda = async () => {
      try {
        const resposta = await axios.get(`http://localhost:8080/sales/${id}`);
        setVenda(resposta.data);
        setNovoStatus(resposta.data.purchaseStatus);
      } catch (err) {
        console.error("Falha ao buscar dados da venda:", err);
        setErro("Não foi possível encontrar a venda. Verifique o ID e tente novamente.");
      } finally {
        setCarregando(false);
      }
    };

    buscarDadosDaVenda();
  }, [id]);


  const handleSalvar = async (event) => {
    event.preventDefault();
    setSalvando(true);

    const dtoParaApi = {
        uuid: venda.saleId,
        user_id: venda.userId,
        event_id: venda.eventId,
        purchaseDate: venda.purchaseDate,
        purchaseStatus: novoStatus,
    };

    try {
      await axios.put(`http://localhost:8080/sales`, dtoParaApi);
      Swal.fire({
        title: 'Sucesso!',
        text: 'Status da venda atualizado com sucesso.',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      router.push('/sales/list'); // Redireciona para a lista de vendas após salvar
    } catch (err) {
      console.error("Falha ao atualizar a venda:", err);
      Swal.fire({
        title: 'Erro!',
        text: 'Ocorreu um erro ao atualizar o status da venda. Tente novamente.',
        icon: 'error',
        confirmButtonText: 'OK'
      });
      setErro("Ocorreu um erro ao salvar as alterações. Tente novamente.");
    } finally {
      setSalvando(false);
    }
  };

  if (carregando) {
    return (
      <div className="d-flex justify-content-center align-items-center" style={{ height: '50vh' }}>
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Carregando...</span>
        </div>
      </div>
    );
  }

  if (erro) {
    return (
      <div className="container mt-5">
        <div className="alert alert-danger" role="alert">
          <strong>Erro:</strong> {erro}
        </div>
        <button className="btn btn-secondary" onClick={() => router.back()}>
          Voltar
        </button>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Editar Status Venda</h2>
      
      {venda && (
        <form onSubmit={handleSalvar}>
          <div className="mb-3">
            <label className="form-label">ID da Venda</label>
            <input type="text" className="form-control" value={venda.saleId} disabled />
          </div>
          <div className="mb-3">
            <label className="form-label">Evento</label>
            <input type="text" className="form-control" value={venda.eventDescription} disabled />
          </div>
          <div className="mb-3">
            <label className="form-label">Data da Compra</label>
            <input type="text" className="form-control" value={venda.purchaseDate} disabled />
          </div>

          <div className="mb-3">
            <label className="form-label">Valor do Ingresso</label>
            <input 
              type="text" 
              className="form-control" 
              value={formatarMoeda(venda.eventPrice)} 
              disabled 
            />
          </div>

          <div className="mb-3">
            <label htmlFor="status" className="form-label">Status da Compra</label>
            <select
              id="status"
              className="form-select"
              value={novoStatus}
              onChange={(e) => setNovoStatus(e.target.value)}
            >
              {OPCOES_STATUS.map(status => (
                <option key={status} value={status}>{status}</option>
              ))}
            </select>
          </div>

          <div className="d-flex gap-2">
            <button type="submit" className="btn btn-primary" disabled={salvando}>
              {salvando ? 'Salvando...' : 'Salvar Alterações'}
            </button>
            <button type="button" className="btn btn-secondary" onClick={() => router.back()}>
              Cancelar
            </button>
          </div>
        </form>
      )}
    </div>
  );
}
