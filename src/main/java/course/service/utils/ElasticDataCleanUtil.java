package course.service.utils;

/**
 * Created by admin on 09.05.2017.
 */
public class ElasticDataCleanUtil {
    
    public static String clean(Object obj) {
        String sourceString = obj.toString();
        while (sourceString.contains("  ")) {
            sourceString = sourceString.replace( "  ", " " );

        }
        sourceString = sourceString.replace("\n","");
        sourceString = sourceString.replace( "{ \"A\": [","" );
        sourceString = sourceString.replace("{ \"type\": \"container\", \"columns\": [ [",
                "");
        sourceString = sourceString.replace("], [",
                "");
        sourceString = sourceString.replace("{ \"type\": \"item\", \"code\": \"","");
        sourceString = sourceString.replace("\" },","");
        sourceString = sourceString.replace("\" }","");
        sourceString = sourceString.replace( "},","");
        sourceString = sourceString.replace("] ","" );
        sourceString = sourceString.replace("} ","");
        sourceString = sourceString.replace("]}","");
        sourceString = sourceString.replace("\\n","");
        sourceString = sourceString.replace("\\","");
        sourceString = sourceString.replaceAll("<(/|)p>", " ");
        return sourceString;
    }
        
}
