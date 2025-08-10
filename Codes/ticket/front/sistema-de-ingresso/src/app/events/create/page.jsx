'use client'
import { useState } from 'react';
import Swal from 'sweetalert2'
import axios from 'axios';


export default function CadastroDeEvento() {
    
    function limparFormulario() {
        setFormData({
            description: '',
            type: '',
            date: '',
            startSales: '',
            endSales: '',
            price: ''
        });
    }

    const [formData, setFormData] = useState({
        description: '',
        type: '',
        date: '',
        startSales: '',
        endSales: '',
        price: ''

    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault(); 
        console.log('Dados do formulário:', formData);
       const response = await axios.post('http://localhost:8080/event', formData);
        console.log('Evento cadastrado:', response.data);
        if (response.status === 200) {
            Swal.fire({
                title: 'Sucesso!',
                text: `Evento ${formData.description} cadastrado com sucesso.`,
                icon: 'success',
                confirmButtonText: 'OK'
            });
            limparFormulario();
        } else {
            Swal.fire({
                title: 'Erro!',
                text: `Erro ao cadastrar evento ${formData.description}: ` + response.data.message,
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
    };

    return (
        <div className="container mt-5 mb-5">
            <div className="row justify-content-center">
                <div className="col-md-8 col-lg-6">
                    
                    <h2 className="mb-4 text-center">Cadastrar Novo Evento</h2>

                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="description" className="form-label">Descrição do Evento</label>
                            <textarea 
                                className="form-control" 
                                id="description" 
                                name="description" 
                                rows="3" 
                                value={formData.description}
                                onChange={handleChange}
                                required
                            ></textarea>
                        </div>

                        <div className="mb-3">
                            <label htmlFor="type" className="form-label">Tipo de Evento</label>
                            <select 
                                className="form-select" 
                                id="type" 
                                name="type" 
                                value={formData.type}
                                onChange={handleChange}
                                required
                            >
                                <option disabled value="">Selecione o tipo...</option>
                                <option value="0">Festa</option>
                                <option value="1">Show</option>
                                <option value="2">Teatro</option>
                            </select>
                        </div>


                        <div className="mb-3">
                            <label htmlFor="date" className="form-label">Data do Evento</label>
                            <input 
                                type="date" 
                                className="form-control" 
                                id="date" 
                                name="date"
                                value={formData.date}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        
                        <hr className="my-4" />

                        <p className="text-muted">Período de Vendas</p>

                        <div className="mb-3">
                            <label htmlFor="startSales" className="form-label">Início das Vendas</label>
                            <input 
                                type="date" 
                                className="form-control" 
                                id="startSales" 
                                name="startSales"
                                value={formData.startSales}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="endSales" className="form-label">Fim das Vendas</label>
                            <input 
                                type="date" 
                                className="form-control" 
                                id="endSales" 
                                name="endSales"
                                value={formData.endSales}
                                onChange={handleChange}
                                required
                            />

                        <div className="mb-3">
                            <label htmlFor="price" className="form-label">Preço do Ingresso</label>
                            <div className="input-group">
                                <span className="input-group-text">R$</span>
                                <input 
                                    type="number" 
                                    className="form-control" 
                                    id="price" 
                                    name="price"
                                    step="0.01" 
                                    min="0" 
                                    placeholder="Ex: 50.00" 
                                    value={formData.price}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                        </div>

                        
                        </div>
                        
                        <div className="d-grid">
                            <button type="submit" className="btn btn-primary btn-lg">Salvar Evento</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    )
    
    
}