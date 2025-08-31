// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

package org.laboratorioordenamiento;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GUIPersonas extends JFrame {
    
    // Componentes principales
    private JTextField txtCedula, txtNombre, txtEstatura, txtEdad;
    private JTable tablaPersonas;
    private DefaultTableModel modeloTabla;
    private JRadioButton rbEstatura, rbEdad, rbMergeSort, rbBurbuja;
    private JButton btnAgregar, btnOrdenar, btnLimpiar, btnEliminar, btnTema;
    private JLabel lblCantidad, lblOrdenActual, lblTiempo, lblTitulo;
    private JPanel panelPrincipal, panelCards, panelTabla;
    
    // Lista de personas
    private List<Persona> listaPersonas;
    
    // Sistema de temas
    private boolean temaOscuro = false;
    private TemaColor temaActual;
    
    // Clase para manejar colores de temas
    private static class TemaColor {
        Color fondo, fondoCard, fondoSecundario, texto, textoSecundario;
        Color primario, secundario, acento, exito, peligro;
        Color gradienteInicio, gradienteFin;
        
        // Tema claro (por defecto)
        static TemaColor claro() {
            TemaColor tema = new TemaColor();
            tema.fondo = new Color(209, 209, 255);
            tema.fondoCard = new Color(193, 205, 200);
            tema.fondoSecundario = new Color(238, 242, 248);
            tema.texto = new Color(45, 55, 72);
            tema.textoSecundario = new Color(113, 128, 150);
            tema.primario = new Color(139, 92, 246);
            tema.secundario = new Color(199, 125, 255);
            tema.acento = new Color(236, 72, 153);
            tema.exito = new Color(34, 197, 94);
            tema.peligro = new Color(239, 68, 68);
            tema.gradienteInicio = new Color(139, 92, 246);
            tema.gradienteFin = new Color(219, 39, 119);
            return tema;
        }
        
        // Tema oscuro
        static TemaColor oscuro() {
            TemaColor tema = new TemaColor();
            tema.fondo = new Color(17, 24, 39);
            tema.fondoCard = new Color(31, 41, 55);
            tema.fondoSecundario = new Color(55, 65, 81);
            tema.texto = new Color(243, 244, 246);
            tema.textoSecundario = new Color(111, 116, 128);
            tema.primario = new Color(147, 197, 253);
            tema.secundario = new Color(109, 107, 126);
            tema.acento = new Color(251, 113, 133);
            tema.exito = new Color(74, 222, 128);
            tema.peligro = new Color(248, 113, 113);
            tema.gradienteInicio = new Color(139, 92, 246);
            tema.gradienteFin = new Color(219, 39, 119);
            return tema;
        }
    }
    
    /**
     * Border personalizado redondeado
     */
    private static class RoundedBorder implements Border {
        private int radius;
        private Color color;
        
        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }
        
        public Insets getBorderInsets(Component c) {
            return new Insets(radius/2, radius/2, radius/2, radius/2);
        }
        
        public boolean isBorderOpaque() {
            return false;
        }
        
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2d.dispose();
        }
    }
    
    /**
     * Panel personalizado con fondo redondeado
     */
    private static class RoundedPanel extends JPanel {
        private int radius;
        private Color backgroundColor;
        
        public RoundedPanel(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Fondo redondeado
            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            
            // Borde sutil
            g2d.setColor(backgroundColor.brighter());
            g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
            
            g2d.dispose();
            super.paintComponent(g);
        }
        
        public void setBackgroundColor(Color color) {
            this.backgroundColor = color;
            repaint();
        }
    }
    
    /**
     * Constructor principal
     */
    public GUIPersonas() {
        listaPersonas = new ArrayList<>();
        temaActual = TemaColor.claro();
        
        configurarVentana();
        inicializarComponentes();
        aplicarTema();
        establecerEventos();
    }
    
    /**
     * Configuración inicial de la ventana
     */
    private void configurarVentana() {
        setTitle("Sistema de Ordenamiento - Lab. Programación 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 700));
    }
    
    /**
     * Inicializa todos los componentes
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JPanel header = crearHeader();
        panelPrincipal.add(header, BorderLayout.NORTH);
        
        // Centro con cards
        panelCards = crearPanelCards();
        JScrollPane scrollCards = new JScrollPane(panelCards);
        scrollCards.setBorder(null);
        scrollCards.getVerticalScrollBar().setUnitIncrement(16);
        scrollCards.setOpaque(false);
        scrollCards.getViewport().setOpaque(false);
        panelPrincipal.add(scrollCards, BorderLayout.CENTER);
        
        // Footer con información
        JPanel footer = crearFooter();
        panelPrincipal.add(footer, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    /**
     * Crea el header con título y botón de tema
     */
    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        
        // Título principal
        lblTitulo = new JLabel("Dashboard de Personas", JLabel.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        
        // Subtítulo
        JLabel subtitulo = new JLabel("Gestiona y ordena personas");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JPanel panelTitulos = new JPanel(new BorderLayout());
        panelTitulos.setOpaque(false);
        panelTitulos.add(lblTitulo, BorderLayout.NORTH);
        panelTitulos.add(subtitulo, BorderLayout.CENTER);
        
        // Botón de tema - SIN EMOJI
        btnTema = crearBotonModerno("Tema", 60, 35);
        btnTema.setToolTipText("Cambiar tema (Claro/Oscuro)");
        
        // Panel para botones de header
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBotones.setOpaque(false);
        panelBotones.add(btnTema);
        
        header.add(panelTitulos, BorderLayout.WEST);
        header.add(panelBotones, BorderLayout.EAST);
        
        return header;
    }
    
    /**
     * Crea el panel principal con cards
     */
    private JPanel crearPanelCards() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        // Card 1: Entrada de datos
        RoundedPanel cardEntrada = crearCardEntradaDatos();
        panel.add(cardEntrada);
        panel.add(Box.createVerticalStrut(20));
        
        // Card 2: Configuración y controles
        RoundedPanel cardControles = crearCardControles();
        panel.add(cardControles);
        panel.add(Box.createVerticalStrut(20));
        
        // Card 3: Tabla de personas
        RoundedPanel cardTabla = crearCardTabla();
        panel.add(cardTabla);
        
        return panel;
    }
    
    /**
     * Crea el card de entrada de datos - REDONDEADO
     */
    private RoundedPanel crearCardEntradaDatos() {
        RoundedPanel card = crearCardRedondeado("+ Agregar Nueva Persona", 
            "Ingresa los datos de la persona que deseas añadir");
        
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Campos de entrada estilizados
        txtCedula = crearCampoModerno("Cedula", 15);
        txtNombre = crearCampoModerno("Nombre completo", 20);
        txtEstatura = crearCampoModerno("Estatura (m)", 15);
        txtEdad = crearCampoModerno("Edad", 15);
        
        // Disposición en grid
        gbc.gridx = 0; gbc.gridy = 0;
        contenido.add(crearLabelModerno("Cedula:"), gbc);
        gbc.gridx = 1;
        contenido.add(txtCedula, gbc);
        
        gbc.gridx = 2; 
        contenido.add(crearLabelModerno("Nombre:"), gbc);
        gbc.gridx = 3;
        contenido.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        contenido.add(crearLabelModerno("Estatura (m):"), gbc);
        gbc.gridx = 1;
        contenido.add(txtEstatura, gbc);
        
        gbc.gridx = 2;
        contenido.add(crearLabelModerno("Edad:"), gbc);
        gbc.gridx = 3;
        contenido.add(txtEdad, gbc);
        
        // Botón agregar con degradado
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnAgregar = crearBotonGradiente("Agregar Persona", temaActual.exito, new Color(34, 197, 94));
        contenido.add(btnAgregar, gbc);
        
        card.add(contenido, BorderLayout.CENTER);
        return card;
    }
    
    /**
     * Crea el card de controles - REDONDEADO
     */
    private RoundedPanel crearCardControles() {
        RoundedPanel card = crearCardRedondeado("Configuracion de Ordenamiento", 
            "Selecciona el criterio y algoritmo para ordenar");
        
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Sección criterio - SIN EMOJIS
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        contenido.add(crearTituloSeccion("Criterio de Ordenamiento:"), gbc);
        
        ButtonGroup grupoCriterio = new ButtonGroup();
        rbEstatura = crearRadioModerno("Por Estatura", true);
        rbEdad = crearRadioModerno("Por Edad", false);
        grupoCriterio.add(rbEstatura);
        grupoCriterio.add(rbEdad);
        
        gbc.gridy = 1; gbc.gridwidth = 1;
        contenido.add(rbEstatura, gbc);
        gbc.gridx = 1;
        contenido.add(rbEdad, gbc);
        
        // Sección algoritmo - SIN EMOJIS
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        contenido.add(crearTituloSeccion("Algoritmo de Ordenamiento:"), gbc);
        
        ButtonGroup grupoAlgoritmo = new ButtonGroup();
        rbMergeSort = crearRadioModerno("MergeSort (Rapido)", true);
        rbBurbuja = crearRadioModerno("Burbuja (Clasico)", false);
        grupoAlgoritmo.add(rbMergeSort);
        grupoAlgoritmo.add(rbBurbuja);
        
        gbc.gridy = 3; gbc.gridwidth = 1;
        contenido.add(rbMergeSort, gbc);
        gbc.gridx = 1;
        contenido.add(rbBurbuja, gbc);
        
        // Botones de acción
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 15, 0));
        panelBotones.setOpaque(false);
        
        btnOrdenar = crearBotonGradiente("Ordenar", temaActual.primario, temaActual.secundario);
        btnEliminar = crearBotonGradiente("Eliminar", temaActual.peligro, new Color(239, 68, 68));
        btnLimpiar = crearBotonGradiente("Limpiar Todo", new Color(107, 114, 128), new Color(75, 85, 99));
        
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        
        contenido.add(panelBotones, gbc);
        
        // Información de estado - SIN EMOJIS
        gbc.gridy = 5; gbc.insets = new Insets(20, 15, 5, 15);
        lblCantidad = crearLabelEstado("Personas: 0");
        contenido.add(lblCantidad, gbc);
        
        gbc.gridy = 6; gbc.insets = new Insets(5, 15, 5, 15);
        lblOrdenActual = crearLabelEstado("Estado: Sin ordenar");
        contenido.add(lblOrdenActual, gbc);
        
        gbc.gridy = 7;
        lblTiempo = crearLabelEstado("Tiempo: --");
        contenido.add(lblTiempo, gbc);
        
        card.add(contenido, BorderLayout.CENTER);
        return card;
    }
    
    /**
     * Crea el card de la tabla - REDONDEADO
     */
    private RoundedPanel crearCardTabla() {
        RoundedPanel card = crearCardRedondeado("Lista de Personas", 
            "Visualiza y gestiona todas las personas registradas");
        
        // Crear tabla moderna - SIN EMOJIS
        String[] columnas = {"Cedula", "Nombre", "Estatura (m)", "Edad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaPersonas = new JTable(modeloTabla);
        estilizarTablaModerna(tablaPersonas);
        
        JScrollPane scrollTabla = new JScrollPane(tablaPersonas);
        scrollTabla.setBorder(new RoundedBorder(12, temaActual.fondoSecundario));
        scrollTabla.setPreferredSize(new Dimension(0, 300));
        
        // Personalizar scrollpane
        scrollTabla.getViewport().setOpaque(false);
        scrollTabla.setOpaque(false);
        
        card.add(scrollTabla, BorderLayout.CENTER);
        return card;
    }
    
    /**
     * Crea un card redondeado con título y descripción
     */
    private RoundedPanel crearCardRedondeado(String titulo, String descripcion) {
        RoundedPanel card = new RoundedPanel(20, temaActual.fondoCard);
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Header del card
        JPanel headerCard = new JPanel(new BorderLayout());
        headerCard.setOpaque(false);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        headerCard.add(lblTitulo, BorderLayout.NORTH);
        headerCard.add(lblDesc, BorderLayout.CENTER);
        
        card.add(headerCard, BorderLayout.NORTH);
        
        return card;
    }
    
    /**
     * Crea un campo de texto moderno y redondeado
     */
    private JTextField crearCampoModerno(String placeholder, int columnas) {
        JTextField campo = new JTextField(columnas) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo redondeado
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        campo.setOpaque(false);
        
        return campo;
    }
    
    /**
     * Crea un label moderno
     */
    private JLabel crearLabelModerno(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return label;
    }
    
    /**
     * Crea un título de sección
     */
    private JLabel crearTituloSeccion(String texto) {
        JLabel titulo = new JLabel(texto);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return titulo;
    }
    
    /**
     * Crea un label de estado
     */
    private JLabel crearLabelEstado(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }
    
    /**
     * Crea un radio button moderno
     */
    private JRadioButton crearRadioModerno(String texto, boolean seleccionado) {
        JRadioButton radio = new JRadioButton(texto, seleccionado);
        radio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        radio.setOpaque(false);
        radio.setFocusPainted(false);
        return radio;
    }
    
    /**
     * Crea un botón moderno simple y redondeado
     */
    private JButton crearBotonModerno(String texto, int width, int height) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo redondeado
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                // Texto
                g2d.setColor(getForeground());
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2d.drawString(getText(), 
                    (getWidth() - textWidth) / 2, 
                    (getHeight() + textHeight) / 2 - 2);
                
                g2d.dispose();
            }
        };
        
        boton.setPreferredSize(new Dimension(width, height));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
    
    /**
     * Crea un botón con degradado personalizado y redondeado
     */
    private JButton crearBotonGradiente(String texto, Color color1, Color color2) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Degradado
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Texto
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2d.drawString(getText(), 
                    (getWidth() - textWidth) / 2, 
                    (getHeight() + textHeight) / 2 - 2);
                
                g2d.dispose();
            }
        };
        
        boton.setPreferredSize(new Dimension(150, 40));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setPreferredSize(new Dimension(152, 42));
                boton.revalidate();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setPreferredSize(new Dimension(150, 40));
                boton.revalidate();
            }
        });
        
        return boton;
    }
    
    /**
     * Estiliza la tabla con diseño moderno
     */
    private void estilizarTablaModerna(JTable tabla) {
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setRowHeight(45);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Header personalizado
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 50));
        tabla.getTableHeader().setOpaque(false);
        
        // Renderer personalizado para las celdas
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(temaActual.primario.brighter());
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? temaActual.fondoCard : temaActual.fondoSecundario);
                    c.setForeground(temaActual.texto);
                }
                
                setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
                return c;
            }
        };
        
        // Aplicar renderer a todas las columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    
    /**
     * Crea el footer
     */
    private JPanel crearFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setOpaque(false);
        
        JLabel lblFooter = new JLabel("Universidad Militar Nueva Granada - Programacion 3");
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.add(lblFooter);
        
        return footer;
    }
    
    /**
     * Aplica el tema actual a todos los componentes
     */
    private void aplicarTema() {
        // Background principal
        panelPrincipal.setBackground(temaActual.fondo);
        
        // Actualizar colores de texto
        lblTitulo.setForeground(temaActual.texto);
        lblCantidad.setForeground(temaActual.textoSecundario);
        lblOrdenActual.setForeground(temaActual.textoSecundario);
        lblTiempo.setForeground(temaActual.acento);
        
        // Actualizar cards redondeados
        actualizarColoresCards();
        
        // Actualizar campos de texto
        actualizarCamposTexto();
        
        // Actualizar tabla
        if (tablaPersonas != null) {
            tablaPersonas.getTableHeader().setBackground(temaActual.fondoSecundario);
            tablaPersonas.getTableHeader().setForeground(temaActual.texto);
            tablaPersonas.repaint();
        }
        
        // Actualizar botón de tema
        if (btnTema != null) {
            btnTema.setBackground(temaActual.primario);
            btnTema.setForeground(Color.WHITE);
        }
        
        repaint();
    }
    
    /**
     * Actualiza los colores de los cards redondeados
     */
    private void actualizarColoresCards() {
        if (panelCards != null) {
            Component[] components = panelCards.getComponents();
            for (Component comp : components) {
                if (comp instanceof RoundedPanel) {
                    ((RoundedPanel) comp).setBackgroundColor(temaActual.fondoCard);
                    actualizarColoresRecursivo(comp);
                }
            }
        }
    }
    
    /**
     * Actualiza colores recursivamente
     */
    private void actualizarColoresRecursivo(Component comp) {
        if (comp instanceof JLabel && !(comp instanceof JTextField)) {
            comp.setForeground(temaActual.texto);
        }
        if (comp instanceof JRadioButton) {
            comp.setForeground(temaActual.texto);
        }
        
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                actualizarColoresRecursivo(child);
            }
        }
    }
    
    /**
     * Actualiza los campos de texto
     */
    private void actualizarCamposTexto() {
        JTextField[] campos = {txtCedula, txtNombre, txtEstatura, txtEdad};
        for (JTextField campo : campos) {
            if (campo != null) {
                campo.setBackground(temaActual.fondoSecundario);
                campo.setForeground(temaActual.texto);
                campo.setCaretColor(temaActual.primario);
            }
        }
    }
    
    /**
     * Establece todos los eventos
     */
    private void establecerEventos() {
        // Botón de tema
        btnTema.addActionListener(e -> cambiarTema());
        
        // Botón agregar
        btnAgregar.addActionListener(e -> agregarPersona());
        
        // Botón ordenar
        btnOrdenar.addActionListener(e -> ordenarPersonas());
        
        // Botón eliminar
        btnEliminar.addActionListener(e -> eliminarPersonaSeleccionada());
        
        // Botón limpiar
        btnLimpiar.addActionListener(e -> limpiarTodo());
        
        // Enter en campos de texto
        ActionListener enterListener = e -> agregarPersona();
        txtCedula.addActionListener(enterListener);
        txtNombre.addActionListener(enterListener);
        txtEstatura.addActionListener(enterListener);
        txtEdad.addActionListener(enterListener);
    }
    
    /**
     * Cambia entre tema claro y oscuro
     */
    private void cambiarTema() {
        temaOscuro = !temaOscuro;
        temaActual = temaOscuro ? TemaColor.oscuro() : TemaColor.claro();
        
        aplicarTema();
        
        // Mostrar mensaje
        String mensaje = temaOscuro ? "Tema oscuro activado" : "Tema claro activado";
        mostrarNotificacion(mensaje);
    }
    
    /**
     * Agrega una nueva persona a la lista
     */
    private void agregarPersona() {
        try {
            // Obtener y validar datos
            String cedula = txtCedula.getText().trim();
            String nombre = txtNombre.getText().trim();
            String estaturaStr = txtEstatura.getText().trim();
            String edadStr = txtEdad.getText().trim();
            
            // Validar campos vacíos
            if (cedula.isEmpty() || nombre.isEmpty() || estaturaStr.isEmpty() || edadStr.isEmpty()) {
                mostrarError("Todos los campos son obligatorios");
                return;
            }
            
            // Validar formato de números
            double estatura = Double.parseDouble(estaturaStr);
            int edad = Integer.parseInt(edadStr);
            
            // Crear persona y validar
            Persona persona = new Persona(cedula, nombre, estatura, edad);
            
            if (!persona.esValida()) {
                mostrarError("Datos invalidos.\nEstatura: 0.5-3.0m, Edad: 1-150 años");
                return;
            }
            
            // Verificar cédula única
            for (Persona p : listaPersonas) {
                if (p.getCedula().equals(cedula)) {
                    mostrarError("Ya existe una persona con esa cedula");
                    return;
                }
            }
            
            // Agregar persona
            listaPersonas.add(persona);
            actualizarTabla();
            limpiarCampos();
            
            mostrarExito("Persona agregada exitosamente");
            
        } catch (NumberFormatException e) {
            mostrarError("Error en formato numerico.\nVerifique estatura y edad");
        } catch (Exception e) {
            mostrarError("Error al agregar persona: " + e.getMessage());
        }
    }
    
    /**
     * Ordena las personas según los criterios seleccionados
     */
    private void ordenarPersonas() {
        if (listaPersonas.isEmpty()) {
            mostrarError("No hay personas para ordenar");
            return;
        }
        
        // Mostrar animación de carga
        mostrarCargando(true);
        
        // Usar SwingWorker para operación en background
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            private long tiempo;
            private String algoritmo;
            
            @Override
            protected Void doInBackground() throws Exception {
                // Configurar criterio de ordenamiento
                if (rbEstatura.isSelected()) {
                    Persona.setCriterioOrdenamiento(Persona.CriterioOrdenamiento.ESTATURA);
                } else {
                    Persona.setCriterioOrdenamiento(Persona.CriterioOrdenamiento.EDAD);
                }
                
                // Crear copia del arreglo para ordenar
                Persona[] arregloPersonas = listaPersonas.toArray(new Persona[0]);
                
                // Aplicar algoritmo seleccionado
                if (rbMergeSort.isSelected()) {
                    tiempo = ordenarConMergeSort(arregloPersonas);
                    algoritmo = "MergeSort";
                } else {
                    tiempo = ordenarConBurbuja(arregloPersonas);
                    algoritmo = "Burbuja";
                }
                
                // Actualizar lista con resultado ordenado
                listaPersonas.clear();
                for (Persona p : arregloPersonas) {
                    listaPersonas.add(p);
                }
                
                // Pequeña pausa para mostrar la animación
                Thread.sleep(300);
                
                return null;
            }
            
            @Override
            protected void done() {
                mostrarCargando(false);
                actualizarTabla();
                
                String criterio = rbEstatura.isSelected() ? "estatura" : "edad";
                lblOrdenActual.setText("Estado: Ordenado por " + criterio + " (mayor a menor)");
                lblTiempo.setText(String.format("Tiempo: %.3f ms (%s)", 
                                tiempo / 1_000_000.0, algoritmo));
                
                mostrarExito("Ordenamiento completado con " + algoritmo);
            }
        };
        
        worker.execute();
    }
    
    /**
     * Elimina la persona seleccionada en la tabla
     */
    private void eliminarPersonaSeleccionada() {
        int filaSeleccionada = tablaPersonas.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            mostrarError("Seleccione una persona para eliminar");
            return;
        }
        
        // Diálogo de confirmación moderno
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Esta seguro de eliminar esta persona?\n\n" + 
            "Esta accion no se puede deshacer.",
            "Confirmar Eliminacion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            listaPersonas.remove(filaSeleccionada);
            actualizarTabla();
            mostrarExito("Persona eliminada");
        }
    }
    
    /**
     * Limpia todos los datos
     */
    private void limpiarTodo() {
        if (listaPersonas.isEmpty()) {
            mostrarError("No hay datos para limpiar");
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Esta seguro de limpiar todos los datos?\n\n" + 
            "Se eliminaran todas las personas registradas.",
            "Confirmar Limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            listaPersonas.clear();
            actualizarTabla();
            limpiarCampos();
            lblOrdenActual.setText("Estado: Sin ordenar");
            lblTiempo.setText("Tiempo: --");
            mostrarExito("Datos limpiados exitosamente");
        }
    }
    
    /**
     * Actualiza la tabla con los datos actuales
     */
    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        // Agregar personas
        for (Persona persona : listaPersonas) {
            modeloTabla.addRow(persona.toTableRow());
        }
        
        // Actualizar contador
        lblCantidad.setText("Personas: " + listaPersonas.size());
        
        // Efecto visual sutil
        lblCantidad.setForeground(temaActual.acento);
        Timer timer = new Timer(300, e -> lblCantidad.setForeground(temaActual.textoSecundario));
        timer.setRepeats(false);
        timer.start();
    }
    
    /**
     * Limpia los campos de entrada
     */
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtEstatura.setText("");
        txtEdad.setText("");
        txtCedula.requestFocus();
    }
    
    /**
     * Ordena usando MergeSort
     */
    private long ordenarConMergeSort(Persona[] arreglo) {
        long inicio = System.nanoTime();
        java.util.Arrays.sort(arreglo);
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Ordena usando Burbuja
     */
    private long ordenarConBurbuja(Persona[] arreglo) {
        long inicio = System.nanoTime();
        
        int n = arreglo.length;
        boolean intercambio;
        
        for (int i = 0; i < n - 1; i++) {
            intercambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arreglo[j].compareTo(arreglo[j + 1]) > 0) {
                    // Intercambiar
                    Persona temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    intercambio = true;
                }
            }
            if (!intercambio) break;
        }
        
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Muestra animación de carga
     */
    private void mostrarCargando(boolean mostrar) {
        if (mostrar) {
            btnOrdenar.setText("Ordenando...");
            btnOrdenar.setEnabled(false);
        } else {
            btnOrdenar.setText("Ordenar");
            btnOrdenar.setEnabled(true);
        }
    }
    
    /**
     * Muestra mensaje de éxito
     */
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra una notificación simple
     */
    private void mostrarNotificacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Notificacion", JOptionPane.INFORMATION_MESSAGE);
    }
}