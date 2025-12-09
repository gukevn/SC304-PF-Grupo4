package cr.ufide.sandwich;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class GestorXML {

    // ============================================================
    // GUARDAR PARTIDA EN XML
    // ============================================================
    public static void guardar(String ruta, Caja caja, Mazo mazo, Mano mano, Pozo pozo) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("partida");
        doc.appendChild(root);

        agregarListaXML(doc, root, "caja", caja.copiarCartas());
        agregarListaXML(doc, root, "mazo", mazo.copiarCartas());
        agregarListaXML(doc, root, "mano", mano.copiarCartas());
        agregarListaXML(doc, root, "pozo", pozo.copiarCartas());

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(
            new DOMSource(doc),
            new StreamResult(new File(ruta))
        );
    }

    private static void agregarListaXML(Document doc, Element root, String nombre, List<Carta> cartas) {
        Element contenedor = doc.createElement(nombre);
        root.appendChild(contenedor);

        for (Carta c : cartas) {
            Element nodoCarta = doc.createElement("carta");
            nodoCarta.setAttribute("palo", c.getPalo().name());
            nodoCarta.setAttribute("valor", String.valueOf(c.getValor()));
            contenedor.appendChild(nodoCarta);
        }
    }

    // ============================================================
    // CARGAR PARTIDA DESDE XML
    // ============================================================
    public static void cargar(String ruta, Caja caja, Mazo mazo, Mano mano, Pozo pozo) throws Exception {

        File archivo = new File(ruta);
        if (!archivo.exists()) throw new Exception("No existe el archivo partida.xml");

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(archivo);

        caja.cargarCartas(leerLista(doc, "caja"));
        mazo.cargarCartas(leerLista(doc, "mazo"));
        mano.cargarCartas(leerLista(doc, "mano"));
        pozo.cargarCartas(leerLista(doc, "pozo"));
    }

    private static List<Carta> leerLista(Document doc, String nombreNodo) {
        List<Carta> lista = new ArrayList<>();

        NodeList contenedor = doc.getElementsByTagName(nombreNodo);
        if (contenedor.getLength() == 0) return lista;

        Node nodo = contenedor.item(0);
        NodeList hijos = nodo.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (!n.getNodeName().equals("carta")) continue;

            NamedNodeMap attrs = n.getAttributes();
            String palo = attrs.getNamedItem("palo").getNodeValue();
            int valor = Integer.parseInt(attrs.getNamedItem("valor").getNodeValue());

            lista.add(new Carta(Carta.Palo.valueOf(palo), valor));
        }

        return lista;
    }
}
