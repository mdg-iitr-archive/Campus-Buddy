
-- Table: android_metadata
CREATE TABLE android_metadata ( 
    locale TEXT DEFAULT 'en_US' 
);

INSERT INTO [android_metadata] ([locale]) VALUES ('en_US');

-- Table: calender
CREATE TABLE calender ( 
    _id       NUMERIC,
    eventdata TEXT 
);

INSERT INTO [calender] ([_id], [eventdata]) VALUES ('DATE', 'EVENTS');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (100713, '*reporting of new Ph.D students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (100713, '*orientation program for all new Ph.D students begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (120713, '*academic regitration of all new Ph.D students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (150713, '*orientation program for all new Ph.D students ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (160713, '*institute reopens');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (170713, '*academic registration of all old students in respective departments/ centres');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (170713, '*academic registration of all new PG students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (180713, '*classes begin for all students (except newly admitted)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (180713, '*classes begin for new PG/Ph.D students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (230713, '*academic registration of all new UG/IDD/IMT/IMS students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (240713, '*orientation program for all new UG/IDD/IMT/IMS students language proficiency test NCC/NSS/NSO selections visits to institute facilities visits to department facilities familiarisation with online registration begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (240713, '*academic registration of PG (against Vacant Seats)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (240713, '*closing of Admission (other than Ph.D)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (260713, '*addition/deletion of courses for old students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (270713, '*online subject registration for new students begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (300713, '*online subject registration for new students ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (300713, '*orientation program for all new UG/IDD/IMT/IMS students language proficiency test NCC/NSS/NSO selections visits to institute facilities visits to department facilities familiarisation with online registration ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (310713, '*classes begin for new UG/IDD/IMT/IMS students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (50813, '*last date for assignment of major projects to all B.Tech. students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (90813, '*Idu''l Fitr (subject to change on visibility of moon)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (150813, '*Independence Day');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (200813, '*notification for MTE schedule');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (280813, '*Janmashtami');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (60913, '*institute elective courses notification to UG/IDD/IMT/IMS students about courses to be offered');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (100913, '*mid term examination begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (130913, '*mid term examination ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (150913, '*engineers’ day');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (160913, '*institute elective courses online subject registration by the UG/IDD/ IMT/IMS students for next semester begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (210913, '*convocation 2013');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (230913, '*institute elective courses online subject registration by the UG/IDD/ IMT/IMS students for next semester ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (230913, '*course withdrawal last date for applying');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (270913, '*warning to students regarding shortage of attendence by the departments');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (300913, '*submission of remaining documents by all new students');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (300913, '*closing of Admission (Ph.D)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (11013, '*online pre-registration for all students for next semester begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (11013, '*online subject pre-registration for all students for next semester begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (21013, '*Mahatma Gandhi''s Birthday');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (41013, '*Thomso 2013 begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (61013, '*Thomso 2013 ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (81013, '*online pre-registration for all students for next semester ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (81013, '*online subject pre-registration for all students for next semester ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (131013, '*Dussehra (Vijay Dashmi)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (161013, '*Idu''l Zuha (Bakrid) (subject to change on visibility of moon)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (211013, '*intimation to parents/guardians in respect of students having short attendence');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (221013, '*notification for ETE schedule');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (281013, '*communication from chairman,DAC/coordinator, first B.tech to course coordinator requesting to submit the final list of students having short attendence on specified format');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (281013, '*filling of response forms for all students begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (311013, '*last date for evaluation of M.Tech. seminar reports');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (31113, '*Diwali (Deepawali)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (41113, '*Govardhan Puja');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (51113, '*Bhai Duj');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (81113, '*classes end for all students ');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (11113, '*filling of response forms for all students ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (111113, '*display of list of students by departments /centres and communication to academic office');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (111113, '*display of marks of course work evaluation');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (111113, '*Diamond and Golden jubilee alumini meet begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (121113, '*Diamond and Golden jubilee alumini meet ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (121113, '*display of list of detained students for ETE by academic office');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (131113, '*end term examination(including practical examination) for all students begins(practical examintion,if necessary,may also be held during last few laboratory days)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (141113, '*Moharram  (subject to change on visibility of moon)');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (171113, '*Guru Nanak''s Birthday');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (181113, '*silver jubilee alumini meet begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (191113, '*silver jubilee alumini meet ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (201113, '*re-examination notification');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (241113, '*end term examination ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (251113, '*Charter/Alumni Day');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (291113, '*finalisation of grades by the grade moderation committee');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (21213, '*submission of progress reports of Ph.D. students by the departments/centres');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (21213, '*winter break for students (other than IID final Yr/PG and Ph.D students) and faculty members begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (21213, '*last date for applying for re-examination');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (61213, '*NCC camp ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (91213, '*re-examination begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (101213, '*re-examination ends');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (161213, '*last date of all examination results by academic office');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (251213, '*Christmas Day');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (261213, '*NCC camp begins');
INSERT INTO [calender] ([_id], [eventdata]) VALUES (311213, '*winter break for students (other than IID final Yr/PG and Ph.D students) and faculty members ends');
