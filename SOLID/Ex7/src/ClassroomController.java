public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // SmartClassroomDevice pj = reg.getFirstOfType("Projector");
        // pj.powerOn();
        // pj.connectInput("HDMI-1");

        // SmartClassroomDevice lights = reg.getFirstOfType("LightsPanel");
        // lights.setBrightness(60);

        // SmartClassroomDevice ac = reg.getFirstOfType("AirConditioner");
        // ac.setTemperatureC(24);

        // SmartClassroomDevice scan = reg.getFirstOfType("AttendanceScanner");
        // System.out.println("Attendance scanned: present=" + scan.scanAttendance());

        reg.getAll(PowerCont.class)
                .forEach(PowerCont::powerOn);

        reg.getAll(BrightnessCont.class)
                .forEach(b -> b.setBrightness(70));

        reg.getAll(TemperatureCont.class)
                .forEach(t -> t.setTemperatureC(22));
    }

    public void endClass() {
        reg.getAll(PowerCont.class)
                .forEach(PowerCont::powerOff);
    }
}
