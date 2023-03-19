-- document
insert into document (`document_no`, `document_title`, `document_content`, `document_type`, `register_member_no`, `register_ymdt`)
values
(1, 'Document 1', 'It is Document 1.', 'DCTY0001', 1, CURRENT_TIMESTAMP),
(2, 'Document 2', 'It is Document 2.', 'DCTY0001', 2, CURRENT_TIMESTAMP),
(3, 'Document 3', 'It is Document 3.', 'DCTY0001', 3, CURRENT_TIMESTAMP),
(4, 'Document 4', 'It is Document 4.', 'DCTY0001', 4, CURRENT_TIMESTAMP),
(5, 'Document 5', 'It is Document 5.', 'DCTY0001', 5, CURRENT_TIMESTAMP),
(6, 'Document 6', 'It is Document 6.', 'DCTY0001', 6, CURRENT_TIMESTAMP),
(7, 'Document 7', 'It is Document 7.', 'DCTY0001', 7, CURRENT_TIMESTAMP),
(8, 'Document 8', 'It is Document 8.', 'DCTY0001', 8, CURRENT_TIMESTAMP),
(9, 'Document 9', 'It is Document 9.', 'DCTY0001', 9, CURRENT_TIMESTAMP);

-- document_approvers
insert into document_approver (`document_approver_no`, `document_no`, `member_no`, `sequence`, `status`, `opinion`, `update_ymdt`, `register_ymdt`)
values
(1, 1, 1, 1, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 1, 2, 2, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 1, 5, 3, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 1, 6, 4, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(5, 2, 3, 1, 'DAST0001', 'ok', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 2, 1, 2, 'DAST0001', 'ok!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 2, 5, 3, 'DAST0001', 'ok', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(8, 3, 1, 1, 'DAST0001', 'ok', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 3, 8, 2, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(10, 4, 3, 1, 'DAST0001', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 4, 5, 2, 'DAST0001', 'ok.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 4, 1, 3, 'DAST0001', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 4, 8, 4, 'DAST0002', 'reject!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(14, 5, 6, 1, 'DAST0001', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 5, 1, 2, 'DAST0001', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(16, 5, 8, 3, 'DAST0002', 'reject.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(17, 6, 3, 1, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 6, 1, 2, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 6, 4, 3, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 6, 5, 4, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(21, 7, 7, 1, 'DAST0001', 'ok!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, 7, 8, 2, 'DAST0001', 'ok!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 7, 9, 3, 'DAST0001', 'ok!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, 7, 1, 4, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(25, 8, 7, 1, 'DAST0002', 'REJECT.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(26, 8, 8, 2, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(27, 8, 9, 3, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(28, 8, 1, 4, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- member
insert into member (`member_no`, `member_id`, `member_password`, `member_name`, `register_ymdt`)
values
(1, 'test1', 'test', 'John', CURRENT_TIMESTAMP),
(2, 'test2', 'test', 'Jane', CURRENT_TIMESTAMP),
(3, 'test3', 'test', 'Smith', CURRENT_TIMESTAMP),
(4, 'test4', 'test', 'Kale', CURRENT_TIMESTAMP),
(5, 'test5', 'test', 'Jack', CURRENT_TIMESTAMP),
(6, 'test6', 'test', 'Jake', CURRENT_TIMESTAMP),
(7, 'test7', 'test', 'Sujan', CURRENT_TIMESTAMP),
(8, 'test8', 'test', 'Andew', CURRENT_TIMESTAMP),
(9, 'test9', 'test', 'Bruno', CURRENT_TIMESTAMP);