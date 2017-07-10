FROM planetek/ubuntu-maven-git:base

ARG branch

WORKDIR /
RUN git clone -b $branch https://teamEreticoTfs:hIEMK-i=d@tfs.planetek.it/SBU-GS/pkz029_UU_CMEMS/_git/pkz029_UU_CMEMS_ESB /src

WORKDIR src/ESB/water_quality
RUN cp -r src/main/resources/legenda /legenda
RUN mvn clean install
WORKDIR target
RUN rm -r */
RUN cp water_quality-1.0.0-SNAPSHOT.zip /opt/mule-standalone/apps
WORKDIR /opt/mule-standalone/bin
CMD ["./mule"]
