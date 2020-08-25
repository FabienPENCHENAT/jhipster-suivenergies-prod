import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD014GenerateurECSDeleteDialogComponent } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs-delete-dialog.component';
import { TD014GenerateurECSService } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.service';

describe('Component Tests', () => {
  describe('TD014GenerateurECS Management Delete Component', () => {
    let comp: TD014GenerateurECSDeleteDialogComponent;
    let fixture: ComponentFixture<TD014GenerateurECSDeleteDialogComponent>;
    let service: TD014GenerateurECSService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD014GenerateurECSDeleteDialogComponent],
      })
        .overrideTemplate(TD014GenerateurECSDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD014GenerateurECSDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD014GenerateurECSService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
