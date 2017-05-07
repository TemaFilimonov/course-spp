package course.service.utils;

import course.domain.Render;

/**
 * Created by admin on 05.05.2017.
 */
public class RenderUtils {

    public static Render build(String source) {
        while (source.contains("  ")) {
            source = source.replace( "  ", " " );

        }
        source = source.replace("\n","");
        source = source.replace( "{ \"A\": ["," <div class=\"container\"> " );
        source = source.replace("{ \"type\": \"container\", \"columns\": [ [",
                "<div class=\"row\"> \n" +
                        "<div class=\"col-md-6\">\n");
        source = source.replace("], [",
                "</div>\n" +
                        "<div class=\"col-md-6\">\n");
        source = source.replace("{ \"type\": \"item\", \"code\": \"","");
        source = source.replace("\" },","");
        source = source.replace("\" }","");
        source = source.replace( "},","");
        source = source.replace("] ","</div>" );
        source = source.replace("} ","");
        source = source.replace("]}","</div>");
        source = source.replace("\\n","");
        source = source.replace("\\","");
        return new Render(source);
    }
}
