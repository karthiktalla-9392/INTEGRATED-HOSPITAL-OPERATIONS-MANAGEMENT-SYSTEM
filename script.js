// --- INITIAL DATA ---
const medicines = [
    {n: "Paracetamol 500", c: "Pain", d: "1-0-1", p: 40},
    {n: "Amoxicillin 250", c: "Antibiotic", d: "1-1-1", p: 120},
    {n: "Metformin 500", c: "Chronic", d: "0-0-1", p: 95},
    {n: "Atorvastatin 10", c: "Chronic", d: "0-0-1", p: 210},
    {n: "Azithromycin 500", c: "Antibiotic", d: "1-0-0", p: 180},
    {n: "Ibuprofen 400", c: "Pain", d: "1-0-1", p: 55},
    {n: "Pantoprazole 40", c: "Gastric", d: "1-0-0", p: 70},
    {n: "Cetirizine 10", c: "Allergy", d: "0-0-1", p: 35}
];

let cart = [];
let appts = [];

// --- AUTH LOGIC ---
function toggleAuth(isReg) {
    document.getElementById('loginFields').style.display = isReg ? 'none' : 'block';
    document.getElementById('registerFields').style.display = isReg ? 'block' : 'none';
    document.getElementById('authTitle').innerText = isReg ? 'Join the Health Revolution' : 'Premium Healthcare Intelligence';
}

function login() {
    const u = document.getElementById('username').value;
    if(u) {
        document.getElementById('loginPage').style.display = 'none';
        document.getElementById('app').style.display = 'block';
        renderPharmacy();
        initChart();
    } else {
        document.getElementById('loginMsg').innerText = "Access Denied. Provide Credentials.";
    }
}

function register() {
    const name = document.getElementById('regName').value;
    if(name) {
        alert(`Welcome to AuraHealth Zenith, ${name}!`);
        toggleAuth(false);
    }
}

// --- NAVIGATION ---
function show(id) {
    document.querySelectorAll('.tab-view').forEach(t => t.style.display = 'none');
    document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
    document.getElementById(id).style.display = 'block';
    
    if(id === 'billing') renderBill();
}

// --- PHARMACY ---
function renderPharmacy() {
    const list = document.getElementById('medList');
    list.innerHTML = medicines.map((m, i) => `
        <tr>
            <td><strong>${m.n}</strong></td>
            <td><span class="category-tag cat-${m.c.toLowerCase()}">${m.c}</span></td>
            <td>${m.d}</td>
            <td>₹${m.p}</td>
            <td><button class="btn btn-brand" style="padding:5px 10px; font-size:11px;" onclick="addCart(${i})">Add to Cart</button></td>
        </tr>
    `).join('');
}

function addCart(i) {
    cart.push(medicines[i]);
    document.getElementById('cartCount').innerText = `🛒 Cart: ${cart.length}`;
    showToast(`${medicines[i].n} added!`);
}

function showToast(msg) {
    const toast = document.createElement('div');
    toast.style = "position:fixed; bottom:20px; right:20px; background:var(--success); color:white; padding:10px 20px; border-radius:10px; z-index:1000";
    toast.innerText = msg;
    document.body.appendChild(toast);
    setTimeout(() => toast.remove(), 2000);
}

// --- BILLING ---
function renderBill() {
    let total = cart.reduce((s, x) => s + x.p, 0);
    document.getElementById('billSummary').innerHTML = cart.map(x => `<p>${x.n} - ₹${x.p}</p>`).join('') + `<hr><h4>Total Due: ₹${total}</h4>`;
}

function pay(mode) {
    if(mode === 'UPI') document.getElementById('qrNode').style.display = 'block';
    else { alert("COD Confirmed."); cart = []; document.getElementById('cartCount').innerText = '🛒 Cart: 0'; show('dash'); }
}

// --- CONSULTATION ---
function startCall() {
    const doc = document.getElementById('videoDoc').value;
    document.getElementById('callStatus').innerText = `Linking with ${doc}...`;
    setTimeout(() => {
        document.getElementById('callStatus').innerHTML = `Connected with ${doc} <br><small style='color:var(--success)'>● Encrypted</small>`;
        document.getElementById('callControls').style.display = 'flex';
    }, 2000);
}

function endCall() {
    document.getElementById('callStatus').innerText = "Session Logged.";
    document.getElementById('callControls').style.display = 'none';
}

// --- APPOINTMENTS ---
function book() {
    const name = document.getElementById('aname').value;
    const doc = document.getElementById('doctor').value;
    const time = document.getElementById('apptTime').value;
    if(!name || !time) return;
    appts.push({name, doc, time: time.replace('T', ' ')});
    document.getElementById('atable').innerHTML = '<tr><th>Patient</th><th>Specialist</th><th>Time</th><th>Status</th></tr>' + 
        appts.map(a => `<tr><td>${a.name}</td><td>${a.doc}</td><td>${a.time}</td><td><b style="color:var(--success)">Confirmed</b></td></tr>`).join('');
}

// --- AI ENGINE ---
function runAI() {
    const input = document.getElementById('aiInput').value.toLowerCase();
    const out = document.getElementById('aiOut');
    out.style.display = 'block';
    out.innerHTML = "<em>Analyzing...</em>";
    setTimeout(() => {
        if(input.includes('chest')) out.innerHTML = "<strong>Alert:</strong> Potential Cardiac Distress.";
        else out.innerHTML = "<strong>Insight:</strong> Common viral activity suggested.";
    }, 1500);
}

// --- LAB REPORTS (PDF) ---
function genLabReport(type, res) {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    doc.text("AuraHealth Zenith Diagnostics", 20, 20);
    doc.text(`Report Type: ${type}`, 20, 40);
    doc.text(`Result Summary: ${res}`, 20, 70);
    doc.save(`${type}_Report.pdf`);
}

// --- CHARTING ---
function initChart() {
    const ctx = document.getElementById('mainChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
            datasets: [{
                label: 'Oxygen Saturation (%)',
                data: [98, 97, 99, 98, 98],
                borderColor: '#8b5cf6',
                fill: true,
                tension: 0.4
            }]
        }
    });
}