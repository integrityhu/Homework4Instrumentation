import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;


public class InstrumentationApp {
   
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("TestInstr");
        CtMethod m = cc.getDeclaredMethod("sayHello");
        m.insertBefore("{ System.out.println(\"method sayHello has been called\"); }");
        cc.writeFile();
        Class<?> c = cc.toClass();
        Object inst = c.newInstance();
        TestInstr t = (TestInstr)inst;
        t.sayHello();
    }

}
