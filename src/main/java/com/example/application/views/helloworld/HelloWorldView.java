package com.example.application.views.helloworld;

import com.example.application.components.TextFilterField;
import com.example.application.model.SlownikOld;
import com.example.application.service.SlownikService;
import com.vaadin.componentfactory.enhancedgrid.EnhancedColumn;
import com.vaadin.componentfactory.enhancedgrid.EnhancedGrid;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private Upload upload;
    private MemoryBuffer memoryBuffer;
    private Div output;
    private SlownikService slownikService;
    private Grid<SlownikOld> grid;
    private List<SlownikOld> lista;
    private ListDataProvider<SlownikOld> listDataProvider;
    private VerticalLayout hl;

    public HelloWorldView(SlownikService slownikService) {
        hl = new VerticalLayout();
        this.slownikService = slownikService;
        lista = new ArrayList<>();
        listDataProvider = new ListDataProvider<>(lista);
        grid = new Grid<>(SlownikOld.class);
//        EnhancedColumn<SlownikOld>pierwszy = grid.addColumn(SlownikOld::getPierwszy).setHeader("Pierwszy");
        grid.setDataProvider(listDataProvider);
        output = new Div();
        memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        upload.setMaxFiles(1);
        upload.setDropLabel(new Label("Dodaj csv"));
        upload.setAcceptedFileTypes("text/csv");
        upload.addSucceededListener(event ->{
            System.out.println("PLIK: "+event.getFileName());
            System.out.println("MIME: "+event.getMIMEType());
            Component component = createComponent(event.getMIMEType(), event.getFileName(), memoryBuffer.getInputStream());
//            output.add(component);
        });
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        hl.add(upload, grid);
        add(hl);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

    private Component createComponent(String mimeType, String fileName,
                                      InputStream stream) {
        if (mimeType.startsWith("text")) {
            return createTextComponent(stream);
        } else if (mimeType.startsWith("image")) {
            Image image = new Image();
            try {

                byte[] bytes = IOUtils.toByteArray(stream);
                image.getElement().setAttribute("src", new StreamResource(
                        fileName, () -> new ByteArrayInputStream(bytes)));
                try (ImageInputStream in = ImageIO.createImageInputStream(
                        new ByteArrayInputStream(bytes))) {
                    final Iterator<ImageReader> readers = ImageIO
                            .getImageReaders(in);
                    if (readers.hasNext()) {
                        ImageReader reader = readers.next();
                        try {
                            reader.setInput(in);
                            image.setWidth(reader.getWidth(0) + "px");
                            image.setHeight(reader.getHeight(0) + "px");
                        } finally {
                            reader.dispose();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setSizeFull();
            return image;
        }
        Div content = new Div();
        String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'",
                mimeType, MessageDigestUtil.sha256(stream.toString()));
        content.setText(text);
        return content;

    }

    private Component createTextComponent(InputStream stream) {
        System.out.println("ROBIE TEXT COMPONENT");
        VerticalLayout vl = new VerticalLayout();
        List<String> text;
        try {
//            text = IOUtils.toString(stream, StandardCharsets.UTF_8);
            text = IOUtils.readLines(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            text = List.of("exception reading stream");
        }
        lista.clear();
        for(String s: text){
            String[] tablica = s.split(";");
            SlownikOld slownik = slownikService.slownikZTablicy(tablica);
            System.out.println("ROZMIAR TABLICY: "+tablica.length);
            System.out.println(slownik.toString());
            lista.add(slownik);
            vl.add(new Text(s));
            vl.add(new Text("DUPA"));
        }
        listDataProvider.refreshAll();
        return vl;
    }

    private void showOutput(String text, Component content,
                            HasComponents outputContainer) {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        outputContainer.add(p);
        outputContainer.add(content);
    }
}
