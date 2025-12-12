import React, { useEffect, useState } from "react";

// Interfaz gráfica de ejemplo para el proyecto (single-file React component)
// - Usa TailwindCSS para estilos (asegúrate de tener Tailwind configurado en tu repo)
// - Reemplaza los endpoints de la API (/api/...) por los tuyos
// - Componente pensado como página principal (Dashboard) para administrar
//   productos, ventas, proveedores, empleados y clientes.

export default function Dashboard() {
  const [active, setActive] = useState("dashboard");
  const [products, setProducts] = useState([]);
  const [clients, setClients] = useState([]);
  const [suppliers, setSuppliers] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [loading, setLoading] = useState(false);
  const [query, setQuery] = useState("");

  // Carga inicial (simulada). Reemplaza por fetch reales (GET /api/products ...)
  useEffect(() => {
    setLoading(true);
    // Simulated data - change to fetch from your backend
    setTimeout(() => {
      setProducts([
        { id: 1, name: "Arroz Llanos", stock: 42, price: 1200 },
        { id: 2, name: "Frijoles Sol", stock: 18, price: 950 },
        { id: 3, name: "Aceite 1L", stock: 60, price: 2400 },
      ]);
      setClients([
        { id: 1, name: "María Pérez", email: "maria@example.com" },
        { id: 2, name: "Carlos Mora", email: "carlos@example.com" },
      ]);
      setSuppliers([
        { id: 1, name: "Proveedor A" },
        { id: 2, name: "Proveedor B" },
      ]);
      setLoading(false);
    }, 500);
  }, []);

  function handleSearch(e) {
    setQuery(e.target.value);
  }

  function filteredProducts() {
    if (!query) return products;
    return products.filter((p) =>
      p.name.toLowerCase().includes(query.toLowerCase())
    );
  }

  function openEdit(product) {
    setSelectedProduct(product);
    setActive("product-edit");
  }

  function saveProduct(changed) {
    // Aquí iría fetch PUT/POST al backend
    setProducts((prev) => prev.map((p) => (p.id === changed.id ? changed : p)));
    setActive("products");
    setSelectedProduct(null);
  }

  return (
    <div className="min-h-screen bg-gray-50 flex font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-white border-r">
        <div className="p-6 border-b">
          <h1 className="text-2xl font-bold">Llanos del Sol</h1>
          <p className="text-sm text-gray-500">Panel de administración</p>
        </div>
        <nav className="p-4">
          <button
            className={`w-full text-left px-4 py-2 rounded-md mb-1 ${
              active === "dashboard" ? "bg-blue-50 text-blue-700" : "hover:bg-gray-100"
            }`}
            onClick={() => setActive("dashboard")}
          >
            Dashboard
          </button>
          <button
            className={`w-full text-left px-4 py-2 rounded-md mb-1 ${
              active === "products" ? "bg-blue-50 text-blue-700" : "hover:bg-gray-100"
            }`}
            onClick={() => setActive("products")}
          >
            Productos
          </button>
          <button
            className={`w-full text-left px-4 py-2 rounded-md mb-1 ${
              active === "sales" ? "bg-blue-50 text-blue-700" : "hover:bg-gray-100"
            }`}
            onClick={() => setActive("sales")}
          >
            Ventas
          </button>
          <button
            className={`w-full text-left px-4 py-2 rounded-md mb-1 ${
              active === "suppliers" ? "bg-blue-50 text-blue-700" : "hover:bg-gray-100"
            }`}
            onClick={() => setActive("suppliers")}
          >
            Proveedores
          </button>
          <button
            className={`w-full text-left px-4 py-2 rounded-md mb-1 ${
              active === "clients" ? "bg-blue-50 text-blue-700" : "hover:bg-gray-100"
            }`}
            onClick={() => setActive("clients")}
          >
            Clientes
          </button>
        </nav>
      </aside>

      {/* Main area */}
      <main className="flex-1 p-6">
        {/* Top bar */}
        <header className="flex items-center justify-between mb-6">
          <div className="flex items-center gap-4">
            <h2 className="text-xl font-semibold capitalize">{active}</h2>
            <div className="hidden md:block">
              <input
                value={query}
                onChange={handleSearch}
                placeholder="Buscar productos..."
                className="px-3 py-2 border rounded-md bg-white"
              />
            </div>
          </div>

          <div className="flex items-center gap-4">
            <button
              onClick={() => alert('Conectar con Google Drive / Exportar CSV (implementar)')}
              className="px-3 py-2 bg-white border rounded-md"
            >
              Exportar
            </button>
            <div className="text-sm text-gray-500">Usuario: Admin</div>
          </div>
        </header>

        {/* Content area */}
        <section>
          {active === "dashboard" && (
            <div>
              <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
                <div className="p-4 bg-white rounded-lg shadow-sm">
                  <div className="text-sm text-gray-500">Productos</div>
                  <div className="text-2xl font-bold">{products.length}</div>
                </div>
                <div className="p-4 bg-white rounded-lg shadow-sm">
                  <div className="text-sm text-gray-500">Clientes</div>
                  <div className="text-2xl font-bold">{clients.length}</div>
                </div>
                <div className="p-4 bg-white rounded-lg shadow-sm">
                  <div className="text-sm text-gray-500">Proveedores</div>
                  <div className="text-2xl font-bold">{suppliers.length}</div>
                </div>
              </div>

              <div className="bg-white p-4 rounded-lg shadow-sm">
                <h3 className="font-semibold mb-2">Resumen rápido</h3>
                <p className="text-sm text-gray-600">
                  Usa esta interfaz como punto de partida. Conecta tus endpoints REST o GraphQL y luego
                  adapta los formularios y tablas según los campos reales de tu base de datos.
                </p>
              </div>
            </div>
          )}

          {active === "products" && (
            <div>
              <div className="flex items-center justify-between mb-4">
                <h3 className="text-lg font-semibold">Lista de productos</h3>
                <button
                  onClick={() => setActive("product-create")}
                  className="px-3 py-2 bg-blue-600 text-white rounded-md"
                >
                  Nuevo producto
                </button>
              </div>

              <div className="overflow-x-auto bg-white rounded-lg shadow-sm">
                <table className="min-w-full text-left">
                  <thead className="border-b">
                    <tr>
                      <th className="p-3">ID</th>
                      <th className="p-3">Nombre</th>
                      <th className="p-3">Stock</th>
                      <th className="p-3">Precio</th>
                      <th className="p-3">Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    {loading && (
                      <tr>
                        <td colSpan={5} className="p-4 text-center text-gray-500">Cargando...</td>
                      </tr>
                    )}
                    {!loading && filteredProducts().map((p) => (
                      <tr key={p.id} className="border-b hover:bg-gray-50">
                        <td className="p-3">{p.id}</td>
                        <td className="p-3">{p.name}</td>
                        <td className="p-3">{p.stock}</td>
                        <td className="p-3">₡ {p.price}</td>
                        <td className="p-3">
                          <button
                            onClick={() => openEdit(p)}
                            className="px-2 py-1 mr-2 border rounded-md text-sm"
                          >
                            Editar
                          </button>
                          <button
                            onClick={() => alert('Eliminar (implementar)')}
                            className="px-2 py-1 border rounded-md text-sm"
                          >
                            Eliminar
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {active === "product-create" && (
            <ProductForm
              onCancel={() => setActive("products")}
              onSave={(newP) => {
                // Simula agregar al backend
                setProducts((prev) => [...prev, { ...newP, id: prev.length + 1 }]);
                setActive("products");
              }}
            />
          )}

          {active === "product-edit" && selectedProduct && (
            <ProductForm
              product={selectedProduct}
              onCancel={() => {
                setSelectedProduct(null);
                setActive("products");
              }}
              onSave={(changed) => saveProduct(changed)}
            />
          )}

          {active === "clients" && (
            <div className="bg-white p-4 rounded-lg shadow-sm">
              <h3 className="font-semibold mb-4">Clientes</h3>
              <ul>
                {clients.map((c) => (
                  <li key={c.id} className="py-2 border-b">{c.name} — {c.email}</li>
                ))}
              </ul>
            </div>
          )}

          {active === "suppliers" && (
            <div className="bg-white p-4 rounded-lg shadow-sm">
              <h3 className="font-semibold mb-4">Proveedores</h3>
              <ul>
                {suppliers.map((s) => (
                  <li key={s.id} className="py-2 border-b">{s.name}</li>
                ))}
              </ul>
            </div>
          )}

          {active === "sales" && (
            <div className="bg-white p-4 rounded-lg shadow-sm">
              <h3 className="font-semibold mb-4">Ventas (Módulo en construcción)</h3>
              <p className="text-sm text-gray-600">Aquí irá el punto de venta y la lista de facturas.</p>
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

function ProductForm({ product = null, onCancel, onSave }) {
  const [form, setForm] = useState(
    product || { name: "", stock: 0, price: 0 }
  );

  useEffect(() => setForm(product || { name: "", stock: 0, price: 0 }), [product]);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: name === "name" ? value : Number(value) }));
  }

  function submit(e) {
    e.preventDefault();
    // Validaciones básicas
    if (!form.name) return alert("El producto necesita un nombre");
    onSave({ ...form, id: product?.id ?? Date.now() });
  }

  return (
    <div className="bg-white p-6 rounded-lg shadow-sm max-w-2xl">
      <h3 className="text-lg font-semibold mb-4">{product ? "Editar producto" : "Crear producto"}</h3>
      <form onSubmit={submit} className="space-y-4">
        <div>
          <label className="block text-sm">Nombre</label>
          <input name="name" value={form.name} onChange={handleChange} className="w-full p-2 border rounded-md" />
        </div>
        <div>
          <label className="block text-sm">Stock</label>
          <input type="number" name="stock" value={form.stock} onChange={handleChange} className="w-full p-2 border rounded-md" />
        </div>
        <div>
          <label className="block text-sm">Precio (¢)</label>
          <input type="number" name="price" value={form.price} onChange={handleChange} className="w-full p-2 border rounded-md" />
        </div>

        <div className="flex gap-2 justify-end">
          <button type="button" onClick={onCancel} className="px-3 py-2 border rounded-md">Cancelar</button>
          <button type="submit" className="px-3 py-2 bg-blue-600 text-white rounded-md">Guardar</button>
        </div>
      </form>
    </div>
  );
}

// NOTAS DE INTEGRACIÓN (añadir al README del repo):
// 1) Añade este archivo a tu carpeta /frontend o /src/pages si usas Next.js.
// 2) Configura TailwindCSS (https://tailwindcss.com/docs/installation).
// 3) Reemplaza las llamadas simuladas por fetch a tus endpoints reales:
//    - GET /api/products
//    - POST /api/products
//    - PUT /api/products/:id
//    - DELETE /api/products/:id
//    - GET /api/clients
//    - GET /api/suppliers
// 4) Si usas autenticación, añade manejo de tokens (Authorization headers) en las fetch.
// 5) Opcional: convertir a TypeScript y añadir validaciones más estrictas.
// 6) Sugerencia de estructura en GitHub: /frontend (React + Tailwind) /backend (API)
// 7) Si quieres, puedo adaptar la interfaz a los campos exactos de tu base de datos.
