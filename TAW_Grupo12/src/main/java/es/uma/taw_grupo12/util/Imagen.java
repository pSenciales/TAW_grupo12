package es.uma.taw_grupo12.util;

import javax.sql.rowset.serial.SerialException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;

public class Imagen {
    public static Clob imageToClob(InputStream in) throws IOException,SQLException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buf = new byte[512];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        String encodedString = Base64.getEncoder().encodeToString(response);
        Clob myClob = new javax.sql.rowset.serial.SerialClob(encodedString.toCharArray());

        return myClob;
    }

    public static String clobToString(Clob c) throws SQLException, IOException {
        Reader r = c.getCharacterStream();
        StringBuffer buffer = new StringBuffer();
        int ch;
        while ((ch = r.read())!=-1) {
            buffer.append("").append((char) ch);
        }
        return buffer.toString();
    }

}
