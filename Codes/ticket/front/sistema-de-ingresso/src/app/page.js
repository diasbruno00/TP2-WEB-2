import Link from 'next/link';


export default function HomePage() {
  return (
    <>
      {/* Seção de Boas-Vindas (Estilo Jumbotron) */}
      <div className="container py-4">
        <div className="p-5 mb-4 bg-light rounded-3 shadow-sm">
          <div className="container-fluid py-5">
            <h1 className="display-5 fw-bold">Sistema de Venda de Ingressos</h1>
            <p className="col-md-8 fs-4">
              Bem-vindo ao painel de gerenciamento. A partir daqui, você pode cadastrar novos eventos,
              registrar vendas, acompanhar o status dos ingressos e atualizar informações de vendas.
            </p>
          </div>
        </div>

        {/* Seção de Ações Principais com Cards */}
        <div className="row align-items-md-stretch">
          
          {/* Card 1: Cadastro de Eventos */}
          <div className="col-md-6 mb-4">
            <div className="h-100 p-5 bg-light rounded-3 d-flex flex-column">
              <h2>
                <i className="bi bi-calendar-plus-fill me-2"></i>
                Cadastro de Eventos
              </h2>
              <p>
                Crie e configure novos eventos. Defina descrições, datas, preços e períodos de venda.
              </p>
              <Link href="/events/create" className="btn btn-outline-secondary mt-auto">
                Criar Novo Evento
              </Link>
            </div>
          </div>

          {/* Card 2: Cadastro de Vendas */}
          <div className="col-md-6 mb-4">
            <div className="h-100 p-5 bg-light border rounded-3 d-flex flex-column">
              <h2>
                <i className="bi bi-ticket-detailed-fill me-2"></i>
                Cadastro de Vendas de Ingressos
              </h2>
              <p>
                Realize a inclusão manual de uma venda de ingresso para um usuário e evento específicos.
              </p>
              <Link href="/sales/create" className="btn btn-outline-secondary mt-auto">
                Registrar Nova Venda
              </Link>
            </div>
          </div>

          
          {/* Card 2: Cadastro de Vendas */}
          <div className="col-md-6 mb-4">
            <div className="h-100 p-5 bg-light border rounded-3 d-flex flex-column">
              <h2>
                <i className="bi bi-ticket-detailed-fill me-2"></i>
                Lista de Vendas e Gerenciamento do Status
              </h2>
              <p>
                 Visualize todas as vendas realizadas. Filtre por status (Pago, Em aberto, Cancelado, etc.) e gerencie cada registro individualmente, alterando seu status conforme necessário.

              </p>
              <Link href="/sales/list" className="btn btn-outline-secondary mt-auto">
                Visualizar Todas as Vendas
              </Link>
            </div>
          </div>



         

          

        </div>
      </div>
    </>
  );
}